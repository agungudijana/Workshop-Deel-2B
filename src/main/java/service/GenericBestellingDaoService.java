package service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.GenericDao;
import pojo.*;

@SuppressWarnings("unchecked")  
@Service("genericBestellingDaoService")
public class GenericBestellingDaoService<T, Id extends Serializable> {
	
	private static final Logger logger =  LoggerFactory.getLogger(GenericBestellingDaoService.class);

	@Autowired
	@Qualifier("bestellingDao")
	private GenericDao bestellingDao;
	
	@Autowired
	@Qualifier("artikelDao")
	private GenericDao artikelDao;
	
	@Autowired
	@Qualifier("bestelArtikelDao")
	private GenericDao bestelArtikelDao;
	
	@Autowired
	@Qualifier("factuurDao")
	private GenericDao factuurDao;
	
	@Autowired
	@Qualifier("betalingDao")
	private GenericDao betalingDao;
	
	
	//===== Bestelling gedeelte =====
	
	@Transactional
	public void persist(Bestelling entity) {
		bestellingDao.persist(entity);
	}
	
	@Transactional
	public void saveOrUpdate(Bestelling entity) {
		bestellingDao.createOrUpdate(entity);
	}
	
	@Transactional
	public Bestelling findBestellingById(long id) {
		Bestelling entity = (Bestelling) bestellingDao.findById(id);
		return entity;
	}
	
	@Transactional
	public void deleteBestelling(Long id) {
		Bestelling entity = (Bestelling)bestellingDao.findById(id);
		System.out.println(entity.getClass() + "will be deleted.");
		bestellingDao.delete(entity);
	}
	
	@Transactional
	public List<Bestelling> findAllBestellingen() {
		List<Bestelling> entities = bestellingDao.findAll();
		return entities;
	}
	
	@Transactional
	public void deleteAllBestellingen() {
		bestellingDao.deleteAll();
	}
	
	
	//===== Artikel gedeelte =====
	
	@Transactional
	public void persist(Artikel entity) {
		artikelDao.persist(entity);
	}
	
	@Transactional
	public void saveOrUpdate(Artikel entity) {
		artikelDao.createOrUpdate(entity);
	}
	
	@Transactional
	public Artikel findArtikelById(long id) {
		Artikel entity = (Artikel) artikelDao.findById(id);
		return entity;
	}
	
	@Transactional
	public void deleteArtikel(Long id) {
		Artikel entity = (Artikel)artikelDao.findById(id);
		System.out.println(entity.getClass() + "will be deleted.");
		artikelDao.delete(entity);
	}
	
	@Transactional
	public List<Artikel> findAllArtikelen() {
		List<Artikel> entities = artikelDao.findAll();
		return entities;
	}
	
	@Transactional
	public void deleteAllArtikellen() {
		artikelDao.deleteAll();
	}
	
	//===== Artikel gedeelte =====
	
	@Transactional
	public void persist(BestelArtikel entity) {
		bestelArtikelDao.persist(entity);
	}
	
	@Transactional
	public void saveOrUpdate(BestelArtikel entity) {
		bestelArtikelDao.createOrUpdate(entity);
	}
	
	@Transactional
	public BestelArtikel findBestelArtikelById(long id) {
		BestelArtikel entity = (BestelArtikel) bestelArtikelDao.findById(id);
		return entity;
	}
	
	@Transactional
	public void deleteBestelArtikel(Long id) {
		BestelArtikel entity = (BestelArtikel)bestelArtikelDao.findById(id);
		System.out.println(entity.getClass() + "will be deleted.");
		bestelArtikelDao.delete(entity);
	}
	
	@Transactional
	public List<BestelArtikel> findAllBestelArtikelen() {
		List<BestelArtikel> entities = bestelArtikelDao.findAll();
		return entities;
	}
	
	@Transactional
	public void deleteAllBestelArtikellen() {
		bestelArtikelDao.deleteAll();
	}
	
	//===== Factuur gedeelte =====
	
	@Transactional
	public void persist(Factuur entity) {
		factuurDao.persist(entity);
	}

	@Transactional
	public void update(Factuur entity) {
		factuurDao.createOrUpdate(entity);
	}
	
	@Transactional
	public Factuur findFactuurById(Long id) {
		Factuur factuur = (Factuur) factuurDao.findById(id);
		return factuur;
	}
	
	@Transactional
	public void deleteFactuur(Long id) {
		Factuur factuur = (Factuur) factuurDao.findById(id);
		System.out.println(factuur + "will be deleted.");
		factuurDao.delete(factuur);
	}
	
	@Transactional
	public List<Factuur> findAllFacturen() {
		List<Factuur> facturen = factuurDao.findAll();
		return facturen;
	}

	@Transactional
	public void deleteAllFacturen() {
		factuurDao.deleteAll();
	}
	
	
	//===== Betaling gedeelte =====
	
	@Transactional
	public void persist(Betaling entity) {
		betalingDao.persist(entity);
	}

	@Transactional
	public void update(KlantAdres entity) {
		betalingDao.createOrUpdate(entity);
	}
	
	@Transactional
	public Betaling findBetalingById(Long id) {
		Betaling betaling = (Betaling) betalingDao.findById(id);
		return betaling;
	}
	
	@Transactional
	public void deleteBetaling(Long id) {
		Betaling betaling = (Betaling) betalingDao.findById(id);
		System.out.println(betaling + "will be deleted.");
		betalingDao.delete(betaling);
	}
	
	@Transactional
	public List<Betaling> findAllBetalingen() {
		List<Betaling> betalingen = betalingDao.findAll();
		return betalingen;
	}

	@Transactional
	public void deleteAllKlantBetalingen() {
		betalingDao.deleteAll();
	}
}
	

