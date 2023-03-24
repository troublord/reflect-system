package com.reflect.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reflect.demo.entity.Authority;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{

}
