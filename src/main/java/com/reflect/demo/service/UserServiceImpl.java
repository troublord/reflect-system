package com.reflect.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reflect.demo.dao.UserDAOJpaImpl;
import com.reflect.demo.entity.User;

@Service
public class UserServiceImpl implements UserService {

	private UserDAOJpaImpl userDAOJpaImpl;
	
	@Autowired
	public UserServiceImpl(UserDAOJpaImpl injecteduserDAO) {
		userDAOJpaImpl = injecteduserDAO;
	}
	
	@Override
	@Transactional
	public List<User> findAll() {
		return userDAOJpaImpl.findAll();
	}

	@Override
	@Transactional
	public User findById(int id) throws Exception {
		User tempUser = userDAOJpaImpl.findById(id);
		if(tempUser == null) {
			throw new RuntimeException("did not find user by id - "+ id);
		}
		return tempUser;
	}

	@Override
	@Transactional
	public void save(User newUser) {
		userDAOJpaImpl.save(newUser);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		userDAOJpaImpl.deleteById(id);
	}

	@Override
	@Transactional
	public void update(User user) {
		// TODO Auto-generated method stub
		userDAOJpaImpl.save(user);
	}

	
}
