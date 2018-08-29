package com.cg.MongoJPA.service;

import java.util.InputMismatchException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.MongoJPA.dao.DAOAccount;
import com.cg.MongoJPA.dao.DAOCustomer;
import com.cg.MongoJPA.pojo.Account;
import com.cg.MongoJPA.pojo.Customer;

@Service
public class ServiceImpl {
	@Autowired
	private DAOAccount daoAccount/*= new DAO()*/;
	private static final Logger logger = LogManager.getLogger(ServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.demo.Account.model.Service#create(java.lang.String)
	 */
	public List<Account> viewAllAccounts() {
		logger.info("Viewing All Accounts in Service");
		return daoAccount.findAll();
	}
	
	public Account viewAccount(int AccountId) throws EntityNotFoundException{
		logger.info("Viewing All Account in Service");
		int flag = 0;
		Account Account = null;
		for(Account c: daoAccount.findAll())
			if(AccountId == c.getAccountNumber()) {
				Account = c;
				flag = 1;
				
			}
		if(flag!=1)
			throw new EntityNotFoundException();
		else
			return Account;
		
	}
	public void saveAccount(Account Account) throws InputMismatchException{
		logger.info("Saving Account in Service");
		if(Account.getAccountNumber()>0)
			daoAccount.save(Account);
		else
			throw new InputMismatchException();
	}

	public int getSizeOfAccounts() {
		logger.info("Viewing Account List Size in Service");
		return (int) daoAccount.count();
	}

	public void updateAccount(Account Account) throws InputMismatchException{
		logger.info("Updating Account in Service");
		Account oldAccount=daoAccount.findById(Account.getAccountNumber()).get();
		if(oldAccount==null)
			throw new InputMismatchException();
		else
			daoAccount.save(Account);
	}

	public void deleteAccount(int AccountId) throws EntityNotFoundException {
		logger.info("Deleting Account in Service");
		int flag = 0;
		for(Account c: daoAccount.findAll())
			if(AccountId == c.getAccountNumber()) {
				daoAccount.deleteById(AccountId);
				flag = 1;
			}
		if(flag!=1)
			throw new EntityNotFoundException();
			
	}
	
	@Autowired
	private DAOCustomer daoCustomer/*= new DAO()*/;
	
	/* (non-Javadoc)
	 * @see com.demo.customer.model.Service#create(java.lang.String)
	 */
	public List<Customer> viewAllCustomers() {
		logger.info("Viewing All Customers in Service");
		return daoCustomer.findAll();
	}
	
	public Customer viewCustomer(int customerId) throws EntityNotFoundException{
		logger.info("Viewing Customer in Service");
		/*int flag = 0;
		Customer customer = null;
		for(Customer c: daoCustomer.findAll())
			if(customerId == c.getCustomerId()) {
				customer = c;
				flag = 1;
				
			}*/
		Customer customer = daoCustomer.findOneByCustomerId(customerId);
		if(customer == null)
			throw new EntityNotFoundException();
		else
			return customer;
	}
	public void saveCustomer(Customer customer) throws InputMismatchException{
		logger.info("Saving Customer in Service");
		if(customer.getCustomerId()>0 && !customer.getName().equals(null))
			daoCustomer.save(customer);
		else
			throw new InputMismatchException();
	}

	public int getSizeOfCustomers() {
		logger.info("Viewing size of Customers in Service");
		return (int) daoCustomer.count();
	}

	public void updateCustomer(Customer customer) throws InputMismatchException{
		logger.info("Viewing All Customers in Service");
//		int flag =0;
		Customer oldCustomer = daoCustomer.findOneByCustomerId(customer.getCustomerId());
		/*for(Customer c: daoCustomer.findAll())
			if(customer.getCustomerId() == c.getCustomerId()) {
				c.setName(customer.getName());
				newCustomer = c;
				flag = 1;
			}*/
		
		if(oldCustomer == null)
			throw new InputMismatchException();
		else {
			oldCustomer.setName(customer.getName());
			daoCustomer.save(oldCustomer);
		}
	}

	public void deleteCustomer(int customerId) throws EntityNotFoundException {
		logger.info("Deleting Customer in Service");
//		int flag = 0;
//		for(Customer c: daoCustomer.findAll())
//			if(customerId == c.getCustomerId()) {
//				daoCustomer.delete(c);;
//				flag = 1;
//			}
		Customer customer = daoCustomer.findOneByCustomerId(customerId);
		
		if(customer == null)
			throw new EntityNotFoundException();
		else
			daoCustomer.delete(customer);
			
	}
}
