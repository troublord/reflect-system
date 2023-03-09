package com.reflect.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reflect.demo.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class UserDAOJpaImpl implements UserDao {

	private EntityManager entityManager;
	
	@Autowired
	public UserDAOJpaImpl(EntityManager injectedEntityManager) {
		entityManager = injectedEntityManager;
	}
	
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
	public void save(User newUser) {
		User user = entityManager.merge(newUser);
		newUser.setId(user.getId());
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

}
