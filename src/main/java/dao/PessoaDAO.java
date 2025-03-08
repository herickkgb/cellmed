package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import generics.GenericDAO;
import model.Pessoa;

public class PessoaDAO extends GenericDAO<Long, Pessoa> {

    private static final long serialVersionUID = 1L;

    public PessoaDAO() {
        super();
    }

    public void salvar(Pessoa pessoa) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pessoa);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void atualizar(Pessoa pessoa) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(pessoa);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void excluir(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Pessoa pessoa = em.find(Pessoa.class, id);
            if (pessoa != null) {
                em.remove(pessoa);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Pessoa buscarPorId(Long id) {
        return getById(id);
    }

    public List<Pessoa> listarTodos() {
        return findAll();
    }

    public Pessoa findByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pessoa p WHERE LOWER(p.email) = :email", Pessoa.class)
                    .setParameter("email", email.toLowerCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
