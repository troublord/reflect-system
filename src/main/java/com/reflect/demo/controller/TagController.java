package com.reflect.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.reflect.demo.entity.Tag;
import com.reflect.demo.service.TagService;

@Controller
public class TagController {
	private final TagService tagService;
	
	@Autowired
	public TagController(TagService tagService) {
		this.tagService = tagService;
	}
	
	@GetMapping("/tags")
	public String showTagsPage(Model model) {
		List<Tag> tags = tagService.getAllTags();
		model.addAttribute("tags", tags);
		Tag tagForCreate = new Tag();
        model.addAttribute("newTag",tagForCreate);
        
        return "tags/showAllTags";
	}
	
	@PostMapping("/tags/save")
	public String createTag(@ModelAttribute("newTag") Tag newTag) {
		tagService.createTag(newTag);
		return "redirect:/tags";
	}
	
	@GetMapping("/tags/{tagId}/delete")
    public String deleteTag(@PathVariable Long tagId) {
		tagService.deleteTagById(tagId);
        return "redirect:/tags";
    }
	@PostMapping("/tags/update")
    public String updateTag(@ModelAttribute("tag") Tag tag) {
		tagService.updateTag(tag);
        return "redirect:/tags";
    }
}
