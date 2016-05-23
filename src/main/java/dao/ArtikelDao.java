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
	
@Repository("artikelDao")
public class ArtikelDao implements AccountDaoInterface<Artikel, Long> {

	private static final Logger logger =  LoggerFactory.getLogger(ArtikelDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
		
		@Override
		public void persist(Artikel entity) {
			sessionFactory.getCurrentSession().persist(entity);
		}

		@Override
	    public void update(Artikel entity) {
	    	sessionFactory.getCurrentSession().update(entity);
		}
	
		@Override
		public Artikel findById(Long id) {
			
			return (Artikel) sessionFactory.getCurrentSession().get(Artikel.class, id);
		
		}
		
		@Override
		public void delete(Artikel entity) {
			sessionFactory.getCurrentSession().delete(entity);
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Artikel> findAll() {
			
			List<Artikel> artikelList = sessionFactory.getCurrentSession().createQuery("from Artikel").list();
			for(Artikel k : artikelList){
				logger.info("Artikel List::"+k);
			}
			return artikelList;
			
		}
		
		@Override
		public void deleteAll() {
			List<Artikel> entityList = findAll();
			
			for (Artikel entity : entityList) {
				delete(entity);
			}
		}


		
	}


