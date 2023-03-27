package com.reflect.demo.dao;

import java.util.List;
import java.util.Optional;

import com.reflect.demo.entity.Action;
import com.reflect.demo.entity.Activity;

public interface ActionDao {
	Action save(Action action);

	Optional<Action> findById(Long id);

    List<Action> findAll();
    
    List<Object[]> findAllForList();

    Action update(Action action);

    void delete(Action action);
    
}
