package com.cg.MongoJPA; 

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cg.MongoJPA.pojo.Account;
import com.cg.MongoJPA.service.ServiceImpl;

@SpringBootApplication
public class MongoJpaApplication {
	private static final Logger logger = LogManager.getLogger(MongoJpaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MongoJpaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner Start(ServiceImpl service) {
		return (args)-> {
		/*	Customer nishad =new Customer("Nishad", 101);
			Customer sayali = new Customer("Sayali", 102);
			Customer drishti =new Customer("Drishti", 103);
			Customer mehek = new Customer("Mehek", 104);
			Customer anindya = new Customer("Anindya", 105);
			service.saveCustomer(nishad);
			service.saveCustomer(sayali);
			service.saveCustomer(drishti);
			service.saveCustomer(mehek);
			service.saveCustomer(anindya);
			System.out.println("Added Customers");*/
			logger.debug("Started");
			service.saveAccount(new Account(101, 10001, 101));
			service.saveAccount(new Account(102, 10002, 102));
			service.saveAccount(new Account(103, 10003, 103));
			service.saveAccount(new Account(104, 10004, 104));
			service.saveAccount(new Account(105, 10005, 105));
			logger.debug("Added Accounts");
			logger.info(service.getSizeOfAccounts()+" Size of Accounts");
			logger.info(service.getSizeOfCustomers()+" Size of Customers");
		};
	}
}
