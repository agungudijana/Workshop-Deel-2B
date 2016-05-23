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

import pojo.*;
	
@Repository("factuurDao")
public class FactuurDao extends GenericDao<Factuur, Long> {  //implements FactuurDaoInterface<Factuur, Long> {

	private static final Logger logger =  LoggerFactory.getLogger(FactuurDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
		public void persist(Factuur entity) {
			sessionFactory.getCurrentSession().save(entity);
		}

	    public void update(Factuur entity) {
	    	sessionFactory.getCurrentSession().update(entity);
		}
	
		public Factuur findById(Long id) {
			Factuur factuur = (Factuur) sessionFactory.getCurrentSession().get(Factuur.class, id);
			return factuur;
		}
	
		public void delete(Factuur entity) {
			sessionFactory.getCurrentSession().delete(entity);
		}

		@SuppressWarnings("unchecked")
		public List<Factuur> findAll() {
			List<Factuur> factuur = (List<Factuur>) sessionFactory.getCurrentSession().createQuery("from Factuur").list();
			return factuur;
		}
	
		public void deleteAll() {
			List<Factuur> entityList = findAll();
			
			for (Factuur entity : entityList) {
				delete(entity);
			}
		}


		
	}

