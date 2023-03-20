package com.reflect.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reflect.demo.dao.UserDAOJpaImpl;
import com.reflect.demo.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAOJpaImpl userDAOJpaImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
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
	public void register(User user) throws Exception {
		//Let's check if user already registered with us
        if(checkIfUserExist(user.getEmail())){
            throw new Exception("User already exists for this email");
        }
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        encodePassword(userEntity, user);
        userDAOJpaImpl.save(userEntity);
	}

	private void encodePassword(User userEntity, User user){
		
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
	}
	
	@Override
	public boolean checkIfUserExist(String email) {
		User user = userDAOJpaImpl.findByEmail(email);
	    if(user == null) {
	    	return false;
	    }
	    return true;
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
