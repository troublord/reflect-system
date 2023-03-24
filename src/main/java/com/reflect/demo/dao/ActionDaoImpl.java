package com.reflect.demo.dao;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reflect.demo.entity.Action;
import com.reflect.demo.entity.Activity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class ActionDaoImpl implements ActionDao{

	@PersistenceContext
	 private EntityManager entityManager;
	@Autowired
    private ActivityDao activityDao;
	
	@Override
	public Action save(Action action) {
		entityManager.persist(action);
	    return action;
	}

	@Override
	public Optional<Action> findById(Long id) {
		return Optional.ofNullable(entityManager.find(Action.class, id));// research yet
	}

	@Override
	public List<Action> findAll() {
		// find current user's activitiesId 
		List<Long> activityIds = activityDao.findAllActivityIds();
		//use activitiesId to query corresponding actions
		TypedQuery<Action> query = entityManager.createQuery("SELECT a FROM Action a "
				+ "WHERE a.activity.id IN :activityIds ORDER BY a.updatedAt DESC", Action.class);
		query.setParameter("activityIds", activityIds);
	    return query.getResultList();
	}
	
	@Override
	public List<Object[]> getActionsForOverview(int listSize) {
		// find current user's activitiesId 
		List<Long> activityIds = activityDao.findAllActivityIds();
		//use activitiesId to query corresponding actions	
		TypedQuery<Object[]> query = entityManager.createQuery("SELECT a.id,a.objective, a.activity.name, a.satisfaction, a.updatedAt"
				+ " FROM Action a"
				+ " WHERE a.activity.id IN :activityIds", Object[].class);
		query.setParameter("activityIds", activityIds);
		query.setMaxResults(listSize);
	    return query.getResultList();
	}

	@Override
	public Action update(Action action) {
		return entityManager.merge(action);
	}

	@Override
	public void delete(Action action) {
		entityManager.remove(action);
	}

	@Override
	public List<Action> findByActivityId(Long activityId) {
		TypedQuery<Action> query = entityManager.createQuery("SELECT a FROM Action a WHERE a.activity.id = :activityId", Action.class);
        query.setParameter("activityId", activityId);
        return query.getResultList();
	}

}
