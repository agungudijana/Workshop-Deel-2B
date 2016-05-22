package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.KlantDao;
import pojo.*;

 
@Service("klantDaoService")
public class KlantDaoService{ //implements KlantDaoServiceInterface {
	
	@Autowired
	private KlantDao klantDao;
	
	public void setKlantDao(KlantDao klantDao) {
		this.klantDao = klantDao;
	}
	
	@Transactional
	public void persist(Klant entity) {
		
		klantDao.persist(entity);
		
	}
	
	@Transactional
	public void update(Klant entity) {
		
		klantDao.update(entity);
		
	}
	
	@Transactional
	public Klant findById(Long id) {
		
		Klant klant = klantDao.findById(id);
		
		return klant;
	}
	
	@Transactional
	public void delete(Long id) {
		
		Klant klant = klantDao.findById(id);
		System.out.println(klant + "will be deleted.");
		klantDao.delete(klant);
		
	}
	
	@Transactional
	public List<Klant> findAll() {
		
		List<Klant> klanten = klantDao.findAll();
		
		return klanten;
	}
	
	@Transactional
	public void deleteAll() {
		
		klantDao.deleteAll();
		
	}

}

