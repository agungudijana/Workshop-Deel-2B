package service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.GenericDao;
import dao.KlantDao;
import pojo.*;

@SuppressWarnings("unchecked")  
@Service("genericDaoService")
public class GenericDaoService<T, Id extends Serializable> {
	
	@Autowired
	@Qualifier("klantDao")
	private GenericDao klantDao;
	
	@Autowired
	@Qualifier("accountDao")
	private GenericDao accountDao;
	
	@Autowired
	@Qualifier("adresDao")
	private GenericDao adresDao;
	
	@Autowired
	@Qualifier("klantAdresDao")
	private GenericDao klantAdresDao;
	
	
	//===== Klant gedeelte =====
	
	@Transactional
	public void persist(Klant entity) {
		klantDao.persist(entity);
	}
	
	@Transactional
	public void saveOrUpdate(Klant entity) {
		klantDao.createOrUpdate(entity);
	}
	
	@Transactional
	public Klant findKlantById(long id) {
		Klant entity = (Klant) klantDao.findById(id);
		return entity;
	}
	
	@Transactional
	public void deleteKlant(Long id) {
		T entity = (T)klantDao.findById(id);
		System.out.println(entity.getClass() + "will be deleted.");
		klantDao.delete(entity);
	}
	
	@Transactional
	public List<Klant> findAllKlanten() {
		List<Klant> entities = klantDao.findAll();
		return entities;
	}
	
	@Transactional
	public void deleteAllKlanten() {
		klantDao.deleteAll();
	}
	
	
	//===== Account gedeelte =====
	
	@Transactional
	public void persist(Account entity) {
		accountDao.persist(entity);
	}
	
	@Transactional
	public void saveOrUpdate(Account entity) {
		klantDao.createOrUpdate(entity);
	}
	
	@Transactional
	public Account findAccountById(long id) {
		Account entity = (Account) accountDao.findById(id);
		return entity;
	}
	
	@Transactional
	public void deleteAccount(Long id) {
		Account entity = (Account)accountDao.findById(id);
		System.out.println(entity.getClass() + "will be deleted.");
		accountDao.delete(entity);
	}
	
	@Transactional
	public List<Account> findAllAccounten() {
		List<Account> entities = accountDao.findAll();
		return entities;
	}
	
	@Transactional
	public void deleteAllAccounten() {
		accountDao.deleteAll();
	}
}


