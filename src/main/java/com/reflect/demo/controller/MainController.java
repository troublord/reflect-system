package com.reflect.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.reflect.demo.entity.User;
import com.reflect.demo.instance.OverviewAction;
import com.reflect.demo.service.ActionService;
import com.reflect.demo.service.UserService;

@Controller
public class MainController {
	
	@Autowired
    private UserService userService;
	
	@Autowired 
	private ActionService actionService;
	
	@GetMapping("/login")
    public String login() {
        return "users/login";
    }
	
	@GetMapping("/registration")
    public String showRegisterForm(Model model) {
		User user = new User();
	    model.addAttribute("user", user);
        return "users/registration";
    }
	@PostMapping("/registration")
    public String register(@ModelAttribute("user") User user, Model model) {
		try {
			userService.createUser(user);
			return "redirect:/login";
		}catch  (IllegalArgumentException e) {
	        // If the user object fails validation, add an error message to the model
	        model.addAttribute("error", e.getMessage());
	        return "users/registration";
	    }
    }
	
	@GetMapping("/home")
    public String home() {
        return "home";
    }
	
	@GetMapping("/overview")
    public String showOverview(Model model) {
		List<OverviewAction> actionList = actionService.getActionsForOverview(15);
		model.addAttribute("OverviewActions",actionList);
        return "overview";
    }
	
	
	
	
}
