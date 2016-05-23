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
	
@Repository("bestellingDao")
public class BestellingDao extends GenericDao<Bestelling, Long> {//implements BestellingDaoInterface<Bestelling, Long> {

	private static final Logger logger =  LoggerFactory.getLogger(BestellingDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
		public void persist(Bestelling entity) {
			sessionFactory.getCurrentSession().save(entity);
		}

	    public void update(Bestelling entity) {
	    	sessionFactory.getCurrentSession().update(entity);
		}
	
		public Bestelling findById(Long id) {
			Bestelling bestellingen = (Bestelling) sessionFactory.getCurrentSession().get(Bestelling.class, id);
			return bestellingen;
		}
	
		public void delete(Bestelling entity) {
			sessionFactory.getCurrentSession().delete(entity);
		}

		@SuppressWarnings("unchecked")
		public List<Bestelling> findAll() {
			List<Bestelling> bestellingen = (List<Bestelling>) sessionFactory.getCurrentSession().createQuery("from Bestelling").list();
			return bestellingen;
		}
	
		public void deleteAll() {
			List<Bestelling> entityList = findAll();
			
			for (Bestelling entity : entityList) {
				delete(entity);
			}
		}


		
	}

