package com.reflect.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import com.reflect.demo.entity.Activity;
import com.reflect.demo.service.IAuthenticationFacade;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class ActivityDaoImpl implements ActivityDao {

	 @PersistenceContext
	 private EntityManager entityManager;
	 
	 @Autowired
	 private IAuthenticationFacade authenticationFacade;
	
	@Override
	public Optional<Activity> findById(Long id) { // return Optional cause it may be null
		
		 return Optional.ofNullable(entityManager.find(Activity.class, id));// research yet
	}
	@Override
	public List<Activity> findAll() {
		Authentication authentication = authenticationFacade.getAuthentication();
		String user_username = authentication.getName();
		TypedQuery<Activity> query = 
				entityManager.createQuery("SELECT a FROM Activity a WHERE a.userUsername = :userUsername  "
						+ "ORDER BY a.status , a.createdAt ", Activity.class);
		query.setParameter("userUsername", user_username);
	    return query.getResultList();
	}

	@Override
	public Activity save(Activity activity) {
		entityManager.persist(activity);
	    return activity;
	}

	@Override
	public Activity update(Activity activity) {
		return entityManager.merge(activity);
	}

	@Override
	public void delete(Activity activity) {
		entityManager.remove(activity);
	}

	@Override
	public List<Long> findAllActivityIds() {
		Authentication authentication = authenticationFacade.getAuthentication();
		String user_username = authentication.getName();
		TypedQuery<Long> query = 
				entityManager.createQuery("SELECT a.id FROM Activity a "
						+ "WHERE a.userUsername = :userUsername", Long.class);
		query.setParameter("userUsername", user_username);
	    return query.getResultList();
	}
	

	
	
}
