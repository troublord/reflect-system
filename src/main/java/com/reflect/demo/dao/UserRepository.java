package com.reflect.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reflect.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
}
