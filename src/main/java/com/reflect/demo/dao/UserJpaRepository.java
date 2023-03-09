package com.reflect.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reflect.demo.entity.User;

public interface UserJpaRepository extends JpaRepository<User, Integer> {
	
}
