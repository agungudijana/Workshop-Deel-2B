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
	
@Repository("betalingDao")
public class BetalingDao extends GenericDao<Betaling, Long> { //implements BetalingDaoInterface<Betaling, Long> {

	private static final Logger logger =  LoggerFactory.getLogger(BetalingDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
		public void persist(Betaling entity) {
			sessionFactory.getCurrentSession().save(entity);
		}

	    public void update(Betaling entity) {
	    	sessionFactory.getCurrentSession().update(entity);
		}
	
		public Betaling findById(Long id) {
			Betaling betaling = (Betaling) sessionFactory.getCurrentSession().get(Betaling.class, id);
			return betaling;
		}
	
		public void delete(Betaling entity) {
			sessionFactory.getCurrentSession().delete(entity);
		}

		@SuppressWarnings("unchecked")
		public List<Betaling> findAll() {
			List<Betaling> betaling = (List<Betaling>) sessionFactory.getCurrentSession().createQuery("from Betaling").list();
			return betaling;
		}
	
		public void deleteAll() {
			List<Betaling> entityList = findAll();
			
			for (Betaling entity : entityList) {
				delete(entity);
			}
		}


		
	}

