package generics;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

import model.IModel;

/**
 * Classe genérica para trabalhar com a persistência de dados
 *
 * @param <PK> Tipo do atributo ID da classe de persistência
 * @param <T>  Classe de persistência
 */
public class GenericDAO<PK, T extends IModel> implements IGenericDAO {

    private static final long serialVersionUID = 1L;
    
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cellmed");
    
    protected EntityManager entityManager;

    // Construtor padrão que inicializa o EntityManager
    public GenericDAO() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    // Construtor que aceita EntityManager (para injeção ou testes)
    public GenericDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Obtém um EntityManager válido
    public EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    // Busca uma entidade pelo ID
    public T getById(PK pk) {
        return getEntityManager().find(getTypeClass(), pk);
    }

    // Salva uma entidade (com transação)
    public void save(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Atualiza uma entidade (com transação)
    public void update(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Exclui uma entidade (com transação)
    public void delete(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Retorna todas as entidades da classe
    public List<T> findAll() {
        return getEntityManager()
                .createQuery("FROM " + getTypeClass().getName(), getTypeClass())
                .getResultList();
    }

    // Retorna o tipo da classe de persistência
    private Class<T> getTypeClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
    }

    // Executa uma consulta HQL e retorna os resultados
    public List<T> listByHql(String hql) {
        return getEntityManager().createQuery(hql, getTypeClass()).getResultList();
    }

    // Executa uma consulta HQL com paginação
    public List<T> listByHql(String hql, int first, int max) {
        Query q = getEntityManager().createQuery(hql, getTypeClass());
        q.setFirstResult(first);
        q.setMaxResults(max);
        return q.getResultList();
    }

    // Executa uma consulta HQL e retorna um único resultado
    public Object findByHql(String hql) {
        return getEntityManager().createQuery(hql).getSingleResult();
    }

    // Executa uma query SQL nativa e converte para classe fornecida
    public <T> T vincularRetornoAoConstrutor(Class<T> tipo, Object[] colunasRetornadas) {
        List<Class<?>> tupleTypes = new ArrayList<>();
        for (Object field : colunasRetornadas) {
            tupleTypes.add(field.getClass());
        }
        try {
            Constructor<T> ctor = tipo.getConstructor(tupleTypes.toArray(new Class<?>[0]));
            return ctor.newInstance(colunasRetornadas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Executa uma consulta SQL nativa e retorna uma lista de objetos mapeados
    public <T> List<T> getResultListParaClasseQueryNativa(Query query, Class<T> tipo) {
        List<Object[]> records = query.getResultList();
        List<T> result = new ArrayList<>();
        for (Object[] record : records) {
            result.add(vincularRetornoAoConstrutor(tipo, record));
        }
        return result;
    }

    // Executa uma consulta SQL nativa e retorna uma lista de objetos mapeados
    public <T> List<T> getResultListParaClasseQueryNativa(String sql, Class<T> tipo) {
        Query query = getEntityManager().createNativeQuery(sql);
        return getResultListParaClasseQueryNativa(query, tipo);
    }

    // Executa uma consulta SQL nativa e retorna um único resultado mapeado
    public <T> T getResultadoUnicoParaClasseQueryNativa(String sql, Class<T> tipo) {
        Query query = getEntityManager().createNativeQuery(sql);
        query.setMaxResults(1);
        List<T> resultados = getResultListParaClasseQueryNativa(query, tipo);
        if (!resultados.isEmpty()) {
            return resultados.get(0);
        }
        throw new NoResultException("Nenhum registro encontrado.");
    }

    // Executa uma query HQL com parâmetros e retorna uma lista de resultados
    public <T> List<T> recuperarListaParaQuery(StringBuilder hql, Map<Integer, Object> parametros, Class<T> tipo) {
        TypedQuery<T> q = getEntityManager().createQuery(hql.toString(), tipo);
        if (parametros != null) {
            parametros.forEach(q::setParameter);
        }
        try {
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
