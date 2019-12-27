package tw.com.dao;

import java.util.List;

import tw.com.entity.Customers;

public interface CustomerDAO {
	List<Customers> findAll();
}
