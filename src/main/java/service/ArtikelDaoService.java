package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.*;
import pojo.*;

 
@Service("artikelDaoService")
public class ArtikelDaoService {
	
	@Autowired
	private ArtikelDao artikelDao;

	public void setArtikelDao(ArtikelDao artikelDao) {
		this.artikelDao = artikelDao;
	}
	
	@Transactional
	public void persist(Artikel entity) {
		artikelDao.persist(entity);
	}
	
	@Transactional
	public void update(Artikel entity) {
		artikelDao.update(entity);
	}
	
	public Artikel findById(Long id) {
		Artikel artikel = artikelDao.findById(id);
		return artikel;
	}
	
	@Transactional
	public void delete(Long id) {
		Artikel artikel = artikelDao.findById(id);
		System.out.println(artikel + "will be deleted.");
		artikelDao.delete(artikel);
	}
	
	@Transactional
	public List<Artikel> findAll() {
		List<Artikel> artikellen = artikelDao.findAll();
		return artikellen;
	}
	
	@Transactional
	public void deleteAll() {
		artikelDao.deleteAll();
	}
}


