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
	
@Repository("bestelArtikelDao")
public class BestelArtikelDao extends GenericDao<BestelArtikel, Long>  { //implements BestelArtikelDaoInterface<BestelArtikel, Long> {

	private static final Logger logger =  LoggerFactory.getLogger(BestelArtikelDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
		public void persist(BestelArtikel entity) {
			sessionFactory.getCurrentSession().save(entity);
		}

	    public void update(BestelArtikel entity) {
	    	sessionFactory.getCurrentSession().update(entity);
		}
	
		public BestelArtikel findById(Long id) {
			BestelArtikel bestellingHasArtikel = (BestelArtikel) sessionFactory.getCurrentSession().get(BestelArtikel.class, id);
			return bestellingHasArtikel;
		}
	
		public void delete(BestelArtikel entity) {
			sessionFactory.getCurrentSession().delete(entity);
		}

		@SuppressWarnings("unchecked")
		public List<BestelArtikel> findAll() {
			List<BestelArtikel> bestellingHasArtikel = (List<BestelArtikel>) sessionFactory.getCurrentSession().createQuery("from BestelArtikel").list();
			return bestellingHasArtikel;
		}
	
		public void deleteAll() {
			List<BestelArtikel> entityList = findAll();
			
			for (BestelArtikel entity : entityList) {
				delete(entity);
			}
		}


		
	}

