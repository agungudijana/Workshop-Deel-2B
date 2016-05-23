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

import pojo.Account;
import pojo.Adres;
import pojo.AdresType;
import pojo.Artikel;
import pojo.BestelArtikel;
import pojo.Bestelling;
import pojo.Betaalwijze;
import pojo.Betaling;
import pojo.Factuur;
import pojo.Klant;
import pojo.KlantAdres;

@Repository("adresDao")
public class AdresDao extends GenericDao<Adres, Long> { //implements AdresDaoInterface<Adres, Long> {
	
private static final Logger logger =  LoggerFactory.getLogger(AdresDao.class);

@Autowired
private SessionFactory sessionFactory;

public void setSessionFactory(SessionFactory sf){
	this.sessionFactory = sf;
}
	

public void persist(Adres entity) {
	sessionFactory.getCurrentSession().save(entity);
	
}

	public void update(Adres entity) {
	sessionFactory.getCurrentSession().update(entity);
	
	}

	public Adres findById(Long id) {
	return (Adres) sessionFactory.getCurrentSession().get(Adres.class, id);
	}


public void delete(Adres entity) {
	sessionFactory.getCurrentSession().delete(entity);
	
}


@SuppressWarnings("unchecked")
public List<Adres> findAll() {
	List<Adres> adressen = (List<Adres>) sessionFactory.getCurrentSession().createQuery("from Adres").list();
	return adressen;
}

public void deleteAll() {
	List<Adres> entityList = findAll();
	
	for (Adres entity : entityList) {
		delete(entity);
	
}
			
}
}
