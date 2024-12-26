package cellmed.dao;

import cellmed.model.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class PessoaDAO {

    private EntityManager entityManager;

    public PessoaDAO() {
        this.entityManager = Persistence.createEntityManagerFactory("cellmedPU").createEntityManager();
    }

    public void salvar(Pessoa pessoa) {
        try {
            entityManager.getTransaction().begin();
            if (pessoa.getId() == 0) {
                entityManager.persist(pessoa);
            } else {
                entityManager.merge(pessoa);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void deletar(Pessoa pessoa) {
        try {
            entityManager.getTransaction().begin();
            pessoa = entityManager.merge(pessoa);
            entityManager.remove(pessoa);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<Pessoa> listar() {
        return entityManager.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
    }

    public Pessoa buscarPorId(int id) {
        return entityManager.find(Pessoa.class, id);
    }
}
