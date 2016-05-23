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
		Klant entity = (Klant)klantDao.findById(id);
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
	
	//===== Adres gedeelte =====
	
	@Transactional
	public void persist(Adres entity) {
		adresDao.persist(entity);
	}

	@Transactional
	public void update(Adres entity) {
		adresDao.createOrUpdate(entity);
	}
	
	@Transactional
	public Adres findAdresById(Long id) {
		Adres adres = (Adres) adresDao.findById(id);
		return adres;
	}
	
	@Transactional
	public void deleteAdres(Long id) {
		Adres adres = (Adres) adresDao.findById(id);
		System.out.println(adres + "will be deleted.");
		adresDao.delete(adres);
	}
	
	@Transactional
	public List<Adres> findAllAdressen() {
		List<Adres> klanten = adresDao.findAll();
		return klanten;
	}

	@Transactional
	public void deleteAllAdressen() {
		adresDao.deleteAll();
	}
	
	
	//===== KlantAdres =====
	
	@Transactional
	public void persist(KlantAdres entity) {
		klantAdresDao.persist(entity);
	}

	@Transactional
	public void update(KlantAdres entity) {
		klantAdresDao.createOrUpdate(entity);
	}
	
	@Transactional
	public KlantAdres findKlantAdresById(Long id) {
		KlantAdres klantAdres = (KlantAdres) klantAdresDao.findById(id);
		return klantAdres;
	}
	
	@Transactional
	public void deleteKlantAdres(Long id) {
		KlantAdres klantAdres = (KlantAdres) klantAdresDao.findById(id);
		System.out.println(klantAdres + "will be deleted.");
		adresDao.delete(klantAdres);
	}
	
	@Transactional
	public List<Adres> findAllKlantAdressen() {
		List<Adres> klanten = klantAdresDao.findAll();
		return klanten;
	}

	@Transactional
	public void deleteAllKlantAdressen() {
		klantAdresDao.deleteAll();
	}
}


