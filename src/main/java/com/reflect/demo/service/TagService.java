package com.reflect.demo.service;

import java.util.List;

import com.reflect.demo.entity.Tag;

public interface TagService {
	Tag createTag(Tag tag);

	Tag getTagById(Long id);
    
	Tag updateTag(Tag tag);

    List<Tag> getAllTags();
    
    void deleteTagById(Long id);
    
    //public PaginationInfo getPaginationInfo();
}
