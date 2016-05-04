package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BetalingDao;
import pojo.*;

 
@Service
public class BetalingDaoService {
	
	@Autowired
	private static BetalingDao betalingDao;

	public BetalingDaoService() {
		betalingDao = new BetalingDao();
	}

	public void persist(Betaling entity) {
		betalingDao.openCurrentSessionwithTransaction();
		betalingDao.persist(entity);
		betalingDao.closeCurrentSessionwithTransaction();
	}

	public void update(Betaling entity) {
		betalingDao.openCurrentSessionwithTransaction();
		betalingDao.update(entity);
		betalingDao.closeCurrentSessionwithTransaction();
	}
	
	//public Betaling findById(String id) {
	public Betaling findById(Long id) {
		betalingDao.openCurrentSession();
		Betaling Betaling = betalingDao.findById(id);
		betalingDao.closeCurrentSession();
		return Betaling;
	}
	
	//public void delete(String id) {
	public void delete(Long id) {
		betalingDao.openCurrentSessionwithTransaction();
		Betaling Betaling = betalingDao.findById(id);
		System.out.println(Betaling + "will be deleted.");
		betalingDao.delete(Betaling);
		betalingDao.closeCurrentSessionwithTransaction();
	}
	
	public List<Betaling> findAll() {
		betalingDao.openCurrentSession();
		List<Betaling> Bestellingen = betalingDao.findAll();
		betalingDao.closeCurrentSession();
		return Bestellingen;
	}

	public void deleteAll() {
		betalingDao.openCurrentSessionwithTransaction();
		betalingDao.deleteAll();
		betalingDao.closeCurrentSessionwithTransaction();
	}

	public BetalingDao betalingDao() {
		return betalingDao;
	}
}

