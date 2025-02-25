package testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {
	protected EntityManager em;

	@Before
	void setUp() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cellmed-test");
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	@After
	void tearDown() {
		if (em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	protected void salvar(Object entity) {
		em.persist(entity);
		em.flush(); // Força a gravação no banco imediatamente
	}
}
