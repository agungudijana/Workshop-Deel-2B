package dao;

import java.sql.*;
import java.util.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pojo.*;

@SuppressWarnings("unchecked")
//@Transactional
@Repository("genericDao")
public abstract class GenericDao<T, Id extends Serializable> implements GenericDaoInterface<T, Id> {

	private static final Logger logger =  LoggerFactory.getLogger(GenericDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> clazz;

    /*public GenericDao(){
        this.clazz =(Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }*/
	
	public GenericDao(){
		  this.clazz =(Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public GenericDao(Class<T> clazz){
        this.clazz = clazz;
    }
	
	public void createOrUpdate(T entity){
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}
	
	public void persist(T entity) {
		sessionFactory.getCurrentSession().save(entity);
	}
	
	public T findById(Id id) {
		//logger.info(clazz.getName() + "findByID method starts");
		//String naam = clazz.getName();
		T entity = (T)sessionFactory.getCurrentSession().get(clazz, id);
		logger.info(clazz + "Klant.findByID method about to end");
		return entity;
	}
	
	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	public List<T> findAll() {
		List<T> entities = (List<T>) sessionFactory.getCurrentSession().createCriteria(clazz).list();
		return entities;
	}
	
	public void deleteAll() {
		List<T> entityList = findAll();
			
		for (T entity : entityList) {
			delete(entity);
		}
	}


		
}

