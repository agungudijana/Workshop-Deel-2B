package dao;

import java.sql.*;
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
import org.springframework.transaction.annotation.Transactional;

import pojo.*;
	

//public class KlantDaoImpl implements KlantDao<Klant, String> {

@Repository("klantDao")
//@Transactional
public class KlantDao implements KlantDaoInterface<Klant, Long> {

	private static final Logger logger =  LoggerFactory.getLogger(KlantDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
		
		@Override
		public void persist(Klant entity) {
			sessionFactory.getCurrentSession().persist(entity);
		}

		@Override
	    public void update(Klant entity) {
	    	sessionFactory.getCurrentSession().update(entity);
		}
	
		@Override
		public Klant findById(Long id) {
			
			return (Klant) sessionFactory.getCurrentSession().get(Klant.class, id);
		
		}
		
		@Override
		public void delete(Klant entity) {
			sessionFactory.getCurrentSession().delete(entity);
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Klant> findAll() {
			
			List<Klant> klantList = sessionFactory.getCurrentSession().createQuery("from Klant").list();
			for(Klant k : klantList){
				logger.info("Person List::"+k);
			}
			return klantList;
			
		}
		
		@Override
		public void deleteAll() {
			List<Klant> entityList = findAll();
			
			for (Klant entity : entityList) {
				delete(entity);
			}
		}


		
	}

