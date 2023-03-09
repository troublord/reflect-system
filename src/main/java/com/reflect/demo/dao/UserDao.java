package com.reflect.demo.dao;

import java.util.List;

import com.reflect.demo.entity.User;

public interface UserDao {
	
	public List<User> findAll();
	
	public User findById(int id);
	
	public void save(User newUser);
	
	public void deleteById(int id);
	
	public void update(User user);
}
