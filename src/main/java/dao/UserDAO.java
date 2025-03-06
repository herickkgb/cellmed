package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Pessoa;
import model.User;

public class UserDAO extends GenericDAO<Long, User> {

    private static final long serialVersionUID = 1L;
    private static final String PERSISTENCE_UNIT_NAME = "cellmed";
    private static EntityManagerFactory factory;
    private PessoaDAO pessoaDAO;

    public UserDAO() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        pessoaDAO = new PessoaDAO();
    }

	public Pessoa findByEmail(String email) {
		return pessoaDAO.findByEmail(email);
	}

    public void save(User user) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    // Método para deletar um usuário
    public void delete(User user) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            // Garante que o usuário esteja no contexto de persistência
            user = em.merge(user);
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    public User findById(Long id) {
        EntityManager em = factory.createEntityManager();
        try {
        	 return em.createQuery("FROM User u WHERE u.id = :id", User.class)
                     .setParameter("id", id)
                     .getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public List<User> findAll() {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("FROM User", User.class).getResultList();
        } finally {
            em.close();
        }
    }
}
