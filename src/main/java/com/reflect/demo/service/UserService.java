package com.reflect.demo.service;

import java.util.List;



import com.reflect.demo.entity.User;

public interface UserService {
	
	public List<User> findAll();
	
	public User findById(int id) throws Exception;
	
	public void save(User newUser);
	
	public void deleteById(int id);
	
	public void update(User user);
}
