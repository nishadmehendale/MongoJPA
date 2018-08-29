package com.cg.MongoJPA.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.MongoJPA.pojo.Account;

@Repository
public interface DAOAccount extends JpaRepository<Account,Integer>{

}
 