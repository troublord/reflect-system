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
		TypedQuery<Action> query = entityManager.createQuery("SELECT a FROM Action a "
				+ " ORDER BY a.updatedAt DESC", Action.class);
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
	public List<Object[]> findAllForList(){
		TypedQuery<Object[]> query = entityManager.createQuery("SELECT a.id,a.objective, a.satisfaction,"
				+ " a.outcome,a.thought, DATE(a.updatedAt) FROM Action a", Object[].class);
	    return query.getResultList();
	}


}
