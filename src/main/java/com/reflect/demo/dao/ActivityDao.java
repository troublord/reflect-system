package com.reflect.demo.dao;

import java.util.List;
import java.util.Optional;

import com.reflect.demo.entity.Activity;

public interface ActivityDao {
	
    Optional<Activity> findById(Long id);

    Activity save(Activity activity);

    Activity update(Activity activity);

    void delete(Activity activity);

	List<Activity> findAll();
	
	List<Long> findAllActivityIds();
}