package com.cg.MongoJPA.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.MongoJPA.pojo.Customer;

@Repository
public interface DAOCustomer extends MongoRepository<Customer, Integer>{
	 Customer findOneByCustomerId(int CustomerId);
}
 