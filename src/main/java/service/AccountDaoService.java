package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.*;
import pojo.*;

 
@Service("accountDaoService")
public class AccountDaoService {
	
	@Autowired
	private AccountDao accountDao;
	
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	@Transactional
	public void persist(Account entity) {
		accountDao.persist(entity);
	}

	public void update(Account entity) {
		accountDao.update(entity);
		}
	
	@Transactional
	public Account findById(Long id) {
		Account account = accountDao.findById(id);
		return account;
	}
	
	//public void delete(String id) {
	public void delete(Long id) {
		Account account = accountDao.findById(id);
		System.out.println(account + "will be deleted.");
		accountDao.delete(account);
	}

	@Transactional
	public List<Account> findAll() {
		List<Account> accounten = accountDao.findAll();
		return accounten;
	}

	@Transactional
	public void deleteAll() {
		accountDao.deleteAll();
	}
}

