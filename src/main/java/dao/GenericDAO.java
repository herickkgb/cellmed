package dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.IModel;
import javax.persistence.Persistence;


/**
 * Classe genérica para trabalhar com a persistência de dados
 *
 * @param <PK> Tipo do atributo ID da classe de persistência
 * @param <T>  Classe de persistência
 */
public class GenericDAO<PK, T extends IModel> implements IGenericDAO {

	private static final long serialVersionUID = 1L;
	protected EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;

    // Construtor
    public GenericDAO() {
        // Se não existir, cria a EntityManagerFactory
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("cellmed");
        }
    }

    // Construtor que aceita EntityManager
    public GenericDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Obtém o EntityManager atual
    public EntityManager getEntityManager() {
        if (this.entityManager == null || !this.entityManager.isOpen()) {
            // Cria uma nova instância de EntityManager se necessário
            this.entityManager = entityManagerFactory.createEntityManager();  // Obtém o EntityManager da fábrica
        }
        return this.entityManager;
    }

    // Busca uma entidade por ID
    @SuppressWarnings("unchecked")
    public T getById(PK pk) {
        return (T) getEntityManager().find(getTypeClass(), pk);
    }

    // Salva uma entidade
    public void save(T entity) {
        getEntityManager().persist(entity);
    }

    // Atualiza uma entidade
    public void update(T entity) {
        getEntityManager().merge(entity);
    }

    // Exclui uma entidade
    public void delete(T entity) {
        getEntityManager().remove(entity);
    }

    // Retorna todas as entidades da classe
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getEntityManager().createQuery("FROM " + getTypeClass().getName()).getResultList();
    }

    // Executa uma consulta HQL e retorna os resultados
    @SuppressWarnings("unchecked")
    public List<T> listByHql(String hql) {
        return getEntityManager().createQuery(hql).getResultList();
    }

    // Executa uma consulta HQL com paginação
    @SuppressWarnings("unchecked")
    public List<T> listByHql(String hql, int first, int max) {
        Query q = getEntityManager().createQuery(hql);
        q.setFirstResult(first);
        q.setMaxResults(max);
        return q.getResultList();
    }

    // Retorna o tipo da classe de persistência
    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
        return clazz;
    }

    // Executa uma consulta HQL e retorna um único resultado
    public Object findByHql(String hql) {
        return getEntityManager().createQuery(hql).getSingleResult();
    }

    // Executa uma consulta SQL nativa e mapeia o retorno para a classe
    public <T> T vincularRetornoAoConstrutor(Class<T> tipo, Object[] colunasRetornadas) {
        List<Class<?>> tupleTypes = new ArrayList<>();
        for (Object field : colunasRetornadas) {
            tupleTypes.add(field.getClass());
        }
        try {
            Constructor<T> ctor = tipo.getConstructor(tupleTypes.toArray(new Class<?>[colunasRetornadas.length]));
            return ctor.newInstance(colunasRetornadas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Executa uma query nativa e mapeia o retorno para a classe
    @SuppressWarnings("unchecked")
    public <T> List<T> getResultListParaClasseQueryNativa(Query query, Class<T> tipo) {
        List<Object[]> records = query.getResultList();
        List<T> result = new ArrayList<>();
        for (Object[] record : records) {
            result.add(vincularRetornoAoConstrutor(tipo, record));
        }
        return result;
    }

    // Executa uma consulta SQL nativa e retorna uma lista de objetos mapeados
    @SuppressWarnings("unchecked")
    public <T> List<T> getResultListParaClasseQueryNativa(String sql, Class<T> tipo) {
        Query query = getEntityManager().createNativeQuery(sql);
        return getResultListParaClasseQueryNativa(query, tipo);
    }

    // Executa uma consulta SQL nativa e retorna um único resultado mapeado
    public <T> T getResultadoUnicoParaClasseQueryNativa(String sql, Class<T> tipo) {
        Query query = getEntityManager().createNativeQuery(sql);
        query.setMaxResults(1);
        List<T> resultados = getResultListParaClasseQueryNativa(query, tipo);
        if (resultados != null && !resultados.isEmpty()) {
            return resultados.get(0);
        }
        throw new NoResultException("Não foram encontrados registros.");
    }

    // Executa uma query HQL com parâmetros e retorna uma lista de resultados
    @SuppressWarnings("hiding")
    public <T> List<T> recuperarListaParaQuery(StringBuilder hql, Map<Integer, Object> parametros, Class<T> tipo) {
        TypedQuery<T> q = getEntityManager().createQuery(hql.toString(), tipo);
        if (parametros != null && !parametros.isEmpty()) {
            for (Integer indiceParametro : parametros.keySet()) {
                Object value = parametros.get(indiceParametro);
                q.setParameter(indiceParametro, value);
            }
        }
        try {
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
