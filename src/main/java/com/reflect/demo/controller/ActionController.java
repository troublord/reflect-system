package com.reflect.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.reflect.demo.entity.Action;
import com.reflect.demo.instance.PaginationInfo;
import com.reflect.demo.service.ActionService;
import com.reflect.demo.service.ActivityService;

@Controller
public class ActionController {

	@Autowired
	private ActionService actionService;
	@Autowired
    private ActivityService activityService;
	
	@GetMapping("/actions")
    public String getAllActions(@RequestParam(name = "page", defaultValue = "1") int page, 
    	      @RequestParam(name = "size", defaultValue = "10") int size,
            Model model) {
        List<Action> actions = actionService.getAllActions(page,size);
        
        PaginationInfo paginationInfo = actionService.getPaginationInfo();
        model.addAttribute("actions", actions);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "actions/showActions";
    }
	
	@GetMapping("/actions/newAction")
    public String showNewActionForm( Model model) {
		Action newAction = new Action();
		model.addAttribute("action",newAction);
        return "actions/newAction";
    }
	
	@PostMapping("actions/save")
	public String createNewAction(@ModelAttribute("action") Action action,Model model) {
		System.out.println(action.toString());
		model.addAttribute("action", action);
		return "redirect:/actions/newAction";
	}
	
}
