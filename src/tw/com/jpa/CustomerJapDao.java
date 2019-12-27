package tw.com.jpa;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import tw.com.dao.CustomerDAO;
import tw.com.entity.Customers;

public class CustomerJapDao implements CustomerDAO {
	
	private EntityManager em;
	public CustomerJapDao(EntityManager em) {
		this.em = em;
	}
	@Override
	public List<Customers> findAll() {
		// TODO Auto-generated method stub
		TypedQuery<Customers> cms = 
				  em.createNamedQuery("findAll",Customers.class);
		List<Customers>	list =  cms.getResultList();
		return list;
	}
}
