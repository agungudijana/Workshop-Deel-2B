package dao;

import java.util.List;

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

@Repository("klantAdresDao")
public class KlantAdresDao extends GenericDao<KlantAdres, Long> { //implements KlantAdresDaoInterface<KlantAdres, Long>{
	private static final Logger logger =  LoggerFactory.getLogger(KlantAdresDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	public void persist(KlantAdres entity) {
		sessionFactory.getCurrentSession().save(entity);
		
	}

	public void update(KlantAdres entity) {
		sessionFactory.getCurrentSession().update(entity);
		
	}

	public KlantAdres findById(Long id) {
		logger.info("KlantAdres.findByID method starts");
		KlantAdres klantAdres = (KlantAdres) sessionFactory.getCurrentSession().get(KlantAdres.class, id);
		logger.info("KlantAdres.findByID method about to end");
		return klantAdres;
	}

	public void delete(KlantAdres entity) {
		sessionFactory.getCurrentSession().delete(entity);
		
	}

	@SuppressWarnings("unchecked")
	public List<KlantAdres> findAll() {
		List<KlantAdres> klantAdressen = (List<KlantAdres>) sessionFactory.getCurrentSession().createQuery("from KlantAdres").list();
		return klantAdressen;
	}

	public void deleteAll() {
		List<KlantAdres> entityList = findAll();
		
		for (KlantAdres entity : entityList) {
			delete(entity);
		
	}
				
	}
	}
