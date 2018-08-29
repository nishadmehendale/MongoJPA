package com.cg.MongoJPA.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.InputMismatchException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.IllegalAddException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.MongoJPA.pojo.Account;
import com.cg.MongoJPA.pojo.Customer;
import com.cg.MongoJPA.service.ServiceImpl;

@RestController
public class Controller {
	@Autowired
	ServiceImpl service;
	private static final Logger logger = LogManager.getLogger(Controller.class);
	
	/* Account REST Endpoints*/
	@RequestMapping(value = "/accounts")
	public ResponseEntity<?> viewAllAccounts() {
		logger.info("Viewing All Accounts");
		List<Account> customers = service.viewAllAccounts();
		if (customers.size() != 0)
			return new ResponseEntity<>(customers, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/accounts/{accountNumber}")
	public ResponseEntity<?> viewAccount(@PathVariable int accountNumber) {
		logger.info("Viewing Account");
		try {
			Account account = service.viewAccount(accountNumber);
			Link link = linkTo(methodOn(this.getClass()).viewAllAccounts()).withRel("View All Accounts");
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Resource resource = new Resource(account,link);
			return new ResponseEntity<>(resource,HttpStatus.OK);
		} catch (EntityNotFoundException ne) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/accounts/add", method = RequestMethod.POST)
	public ResponseEntity<?> addAccount(@RequestBody Account account) {
		logger.info("Adding Account");
		try {
			service.saveAccount(account);
			Link link = linkTo(methodOn(this.getClass()).viewAccount(account.getAccountNumber())).withRel("View Account");
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Resource resource = new Resource(link);
			return new ResponseEntity<>(resource, HttpStatus.OK);
		} catch (IllegalAddException ne) {
			return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		}
	}

	@RequestMapping(value = "/accounts/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAccount(@RequestBody Account account) {
		logger.info("Updating Account");
		try {
			service.updateAccount(account);
			Link link = linkTo(methodOn(this.getClass()).viewAccount(account.getAccountNumber())).withRel("View Account");
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Resource resource = new Resource(link);
			return new ResponseEntity<>(resource, HttpStatus.OK);
		} catch (InputMismatchException ne) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}
	@RequestMapping(value = "/accounts/delete/{accountNumber}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAccount(@PathVariable int accountNumber) {
		logger.info("Deleting Account");
		try {
			service.deleteCustomer(accountNumber);
			Link link = linkTo(methodOn(this.getClass()).viewAllAccounts()).withRel("View All Accounts");
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Resource resource = new Resource(link);
			return new ResponseEntity<>(resource,HttpStatus.OK);
		} catch (EntityNotFoundException ne) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}
	
	
	/* Customer REST Endpoints*/
	
	
	@RequestMapping(value = "/customers")
	public ResponseEntity<?> viewAllCustomer() {
		logger.info("Viewing All Customers");
		List<Customer> customers = service.viewAllCustomers();
		if (customers.size() != 0)
			return new ResponseEntity<>(customers, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/customers/{customerId}")
	public ResponseEntity<?> viewCustomer(@PathVariable int customerId) {
		logger.info("Viewing Customer");
		try {
			Link link = linkTo(methodOn(this.getClass()).viewAllCustomer()).withRel("View All Customers");
			Customer customer = service.viewCustomer(customerId);
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Resource resource = new Resource(customer,link);
			return new ResponseEntity<>(resource, HttpStatus.OK);
		} catch (EntityNotFoundException ne) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/customers/add", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		logger.info("Adding Customer");
		try {
			service.saveCustomer(customer);
			Link link = linkTo(methodOn(this.getClass()).viewCustomer(customer.getCustomerId())).withRel("View Customer");
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Resource resource = new Resource(link);
			return new ResponseEntity<>(resource, HttpStatus.OK);
		} catch (IllegalAddException ne) {
			return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		}
	}

	@RequestMapping(value = "/customers/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
		logger.info("Updating Customer");
		try {
			service.updateCustomer(customer);
			Link link = linkTo(methodOn(this.getClass()).viewCustomer(customer.getCustomerId())).withRel("View All Customers");
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Resource resource = new Resource(link);
			return new ResponseEntity<>(resource, HttpStatus.OK);
		} catch (InputMismatchException ne) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	@RequestMapping(value = "/customers/delete/{customerId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) {
		logger.info("Deleting Customer");
		try {
			Link link = linkTo(methodOn(this.getClass()).viewAllCustomer()).withRel("View Customer");
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Resource resource = new Resource(link);
			service.deleteCustomer(customerId);
			return new ResponseEntity<>(resource,HttpStatus.OK);
		} catch (EntityNotFoundException ne) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}
}
