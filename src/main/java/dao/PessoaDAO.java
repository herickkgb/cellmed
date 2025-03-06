package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import model.Pessoa;

public class PessoaDAO extends GenericDAO<Long, Pessoa>{

	private static final long serialVersionUID = 1L;
	private static final String PERSISTENCE_UNIT_NAME = "cellmed";
    private static EntityManagerFactory factory;

    public PessoaDAO() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public void salvar(Pessoa pessoa) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pessoa);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void atualizar(Pessoa pessoa) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(pessoa);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void excluir(Long id) {
        EntityManager em = factory.createEntityManager();
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
        EntityManager em = factory.createEntityManager();
        try {
            return em.find(Pessoa.class, id);
        } finally {
            em.close();
        }
    }

    public List<Pessoa> listarTodos() {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
        } finally {
            em.close();
        }
    }

	public void excluir(Pessoa pessoa) {
		 EntityManager em = factory.createEntityManager();
	        try {
	            em.getTransaction().begin();
	            Pessoa pessoaDel = em.find(Pessoa.class, pessoa.getId());
	            if (pessoaDel != null) {
	                em.remove(pessoaDel);
	            }
	            em.getTransaction().commit();
	        } finally {
	            em.close();
	        }
		
	}

	public Pessoa findByEmail(String email) {
        EntityManager em = factory.createEntityManager();
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
