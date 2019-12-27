package tw.com.cdi;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class EntityManagerProvider {
	@PersistenceContext
	@Produces
	private EntityManager em;
}
