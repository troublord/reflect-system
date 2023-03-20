package com.reflect.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflect.demo.entity.User;

public interface UserDao {
	
	public List<User> findAll();
	
	public User findById(int id);
	
	public User save(User newUser);
	
	public void deleteById(int id);
	
	public void update(User user);
	
	User findByEmail(String email);
}
