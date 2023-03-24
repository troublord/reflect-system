package com.reflect.demo.service;

import java.util.List;
import java.util.Optional;

import com.reflect.demo.entity.User;

public interface UserService {
	
	 User createUser(User user);

	 Optional<User> getUserByUsername(String username);

	 List<User> getAllUsers();

	 boolean deleteUserByUsername(String username);
}
