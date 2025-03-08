package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import generics.GenericDAO;
import model.Pessoa;
import model.User;

public class UserDAO extends GenericDAO<Long, User> {

    private static final long serialVersionUID = 1L;
    private final PessoaDAO pessoaDAO;

    public UserDAO() {
        super();
        this.pessoaDAO = new PessoaDAO();
    }

    // Busca uma pessoa pelo email utilizando PessoaDAO
    public Pessoa findByEmail(String email) {
        return pessoaDAO.findByEmail(email);
    }

    // Salva ou atualiza um usuário
    public void save(User user) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    // Exclui um usuário
    public void delete(User user) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(user) ? user : em.merge(user));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // Busca um usuário pelo ID
    public User findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("FROM User u WHERE u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    // Lista todos os usuários
    public List<User> findAll() {
        return super.findAll();
    }
}
