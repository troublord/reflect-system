package com.reflect.demo.dao;

import java.util.List;
import java.util.Optional;

import com.reflect.demo.entity.Tag;


public interface TagDao {
	Optional<Tag> findById(Long id);

	Tag save(Tag tag);

	Tag update(Tag tag);

    void delete(Tag tag);

	List<Tag> findAll();

}
