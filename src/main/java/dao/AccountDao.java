package dao;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pojo.*;
	
@Repository("accountDao")
public class AccountDao extends GenericDao<Account, Long> implements AccountDaoInterface<Account, Long> {

	private static final Logger logger =  LoggerFactory.getLogger(AccountDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
		
		@Override
		public void persist(Account entity) {
			sessionFactory.getCurrentSession().persist(entity);
		}

		@Override
	    public void update(Account entity) {
	    	sessionFactory.getCurrentSession().update(entity);
		}
	
		@Override
		public Account findById(Long id) {
			
			return (Account) sessionFactory.getCurrentSession().get(Account.class, id);
		
		}
		
		@Override
		public void delete(Account entity) {
			sessionFactory.getCurrentSession().delete(entity);
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Account> findAll() {
			
			List<Account> accountList = sessionFactory.getCurrentSession().createQuery("from Account").list();
			for(Account k : accountList){
				logger.info("Account List::"+k);
			}
			return accountList;
			
		}
		
		@Override
		public void deleteAll() {
			List<Account> entityList = findAll();
			
			for (Account entity : entityList) {
				delete(entity);
			}
		}


		
	}


