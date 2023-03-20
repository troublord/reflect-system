package com.reflect.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.reflect.demo.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class UserDAOJpaImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<User> findAll() {
		Query query = entityManager.createQuery("from User");
		List<User> users = query.getResultList();
		
		return users;
	}

	@Override
	public User findById(int id) {
		User user = entityManager.find(User.class,id);
		return user;
	}

	@Override
	public User save(User newUser) {
		entityManager.persist(newUser);
		return newUser;
	}

	@Override
	public void deleteById(int id) {
		Query query = entityManager.createQuery("delete from User where id=:userId");
		query.setParameter("userId", id);
		query.executeUpdate();

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findByEmail(String email) {
		Query query = entityManager.createQuery(" FROM User WHERE email = :email");
		query.setParameter("email", email);
		List<User> users = query.getResultList();
		if (!users.isEmpty()) {
			return users.get(0);
		} 
		
		return null;
	}

	

}
