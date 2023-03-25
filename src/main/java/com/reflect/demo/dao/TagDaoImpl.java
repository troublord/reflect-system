package com.reflect.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reflect.demo.entity.Action;
import com.reflect.demo.entity.Tag;
import com.reflect.demo.entity.User;
import com.reflect.demo.service.UserService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class TagDaoImpl implements TagDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Optional<Tag> findById(Long id) {
		return Optional.ofNullable(entityManager.find(Tag.class, id));
	}

	@Override
	public Tag save(Tag tag) {
		//set user
		User currentUser = userService.getCurrentUser();
		tag.setUser(currentUser);
		entityManager.persist(tag);
	    return tag;
	}

	@Override
	public Tag update(Tag tag) {
		User currentUser = userService.getCurrentUser();
		tag.setUser(currentUser);
		entityManager.merge(tag);
	    return tag;
	}

	@Override
	public void delete(Tag tag) {
		entityManager.remove(tag);
	}

	@Override
	public List<Tag> findAll() {
		Long currentUserId = userService.getCurrentUserId();
		TypedQuery<Tag> query = entityManager.createQuery("SELECT a FROM Tag a "
				+ "WHERE a.user.nonKeyId =:userId", Tag.class);
		query.setParameter("userId", currentUserId+"");
	    return query.getResultList();
	}

}
