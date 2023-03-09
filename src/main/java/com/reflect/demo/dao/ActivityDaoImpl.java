package com.reflect.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.reflect.demo.entity.Activity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class ActivityDaoImpl implements ActivityDao {

	 @PersistenceContext
	 private EntityManager entityManager;
	
	@Override
	public Optional<Activity> findById(Long id) { // return Optional cause it may be null
		
		 return Optional.ofNullable(entityManager.find(Activity.class, id));// research yet
	}
	@Override
	public List<Activity> findAll() {
		TypedQuery<Activity> query = entityManager.createQuery("SELECT a FROM Activity a", Activity.class);
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
	public List<Activity> findByUserId(Long userId) {
		 TypedQuery<Activity> query = entityManager.createQuery(
	                "SELECT a FROM Activity a WHERE a.user.id = :userId", Activity.class);
	        query.setParameter("userId", userId);
	        return query.getResultList();
	}

}
