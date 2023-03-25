package com.reflect.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reflect.demo.dao.TagDao;
import com.reflect.demo.entity.Tag;

@Service
public class TagServiceImpl implements TagService {

	private final TagDao tagDao;
	
	@Autowired
	public TagServiceImpl(TagDao tagDao) {
		this.tagDao = tagDao;
	}
	
	@Override
	@Transactional
	public Tag createTag(Tag tag) {
		return tagDao.save(tag);
	}

	@Override
	public Tag getTagById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Tag updateTag(Tag tag) {
		return tagDao.update(tag);
	}

	@Override
	@Transactional
	public List<Tag> getAllTags() {
		return tagDao.findAll();
	}

	@Override
	@Transactional
	public void deleteTagById(Long id) {
		Optional<Tag> resultOpt = tagDao.findById(id);
		if(resultOpt.isPresent()) {
			tagDao.delete(resultOpt.get());
		}
	}

}
