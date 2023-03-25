package com.reflect.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reflect.demo.dao.AuthorityRepository;
import com.reflect.demo.dao.UserRepository;
import com.reflect.demo.entity.Authority;
import com.reflect.demo.entity.User;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;
	private final PasswordEncoder passwordEncoder;
	private final IAuthenticationFacade authenticationFacade;
	
	@Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
    		AuthorityRepository authorityRepository,IAuthenticationFacade authenticationFacade) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.authenticationFacade = authenticationFacade;
    }
	
	@Override
	@Transactional
	public User createUser(User user) {
		// Validate the user object
        validateUser(user);
        
        // Copy the user object to a new User object
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        
        // Hash the password using a secure algorithm
        String encryptedPassword = encryptPassword(newUser.getPassword());
        newUser.setPassword(encryptedPassword);
        newUser.setEnabled(true);
        // Save the user object to the database and add authority
        userRepository.save(newUser);
        Authority newAuthority = new Authority(newUser,"GENERAL");
        authorityRepository.save(newAuthority);

        return newUser;
	}

	@Override
	@Transactional
	public Optional<User> getUserByUsername(String username) {
		return userRepository.findById(username);
	}

	@Override
	@Transactional
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public boolean deleteUserByUsername(String username) {
		if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
            return true;
        }
        return false;
	}

	private void validateUser(User user) throws IllegalArgumentException {
        // Check that the username and password are not empty
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be empty");
        }

        // Check that the username does not already exist in the database
        if (userRepository.existsById(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
    }
	private String encryptPassword(String password) {
        
        return passwordEncoder.encode(password);
    }
	
	@Override
	public String getCurrentUserName() {
		Authentication authentication = authenticationFacade.getAuthentication();
		return authentication.getName();
	}
	
	@Override
	public Long getCurrentUserId() {
		String username = getCurrentUserName();
		Optional<User> currentUserOpt = getUserByUsername(username);
		Long result = 0l;
		if(currentUserOpt.isPresent()) {
			result = currentUserOpt.get().getNonKeyId();
		}
		return result;
	}
	
	@Override
	public User getCurrentUser() {
		String username = getCurrentUserName();
		Optional<User> currentUserOpt = getUserByUsername(username);
		User currentUser;
		if(currentUserOpt.isPresent()) {
			currentUser = currentUserOpt.get();
		}else {
			currentUser = new User();
		}
		return currentUser;
	}
	
}
