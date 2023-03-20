package com.reflect.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.reflect.demo.entity.User;
import com.reflect.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService injectedUserService) {
		userService = injectedUserService;
	}
	
	@GetMapping("/list")
	public String findAll(Model model){
		List<User> users = userService.findAll();
		model.addAttribute("users",users);
		return "users/userList";
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm(Model model) {
		User theUser = new User();
		model.addAttribute("user" , theUser);
		return "users/registration";
	}
	@PostMapping("/registration/saving")
	public String registration(@ModelAttribute("user")@Validated User user,BindingResult bindingResult,Model model) throws Exception {
		  if(bindingResult.hasErrors()){
	            model.addAttribute("user", user);
	            return "users/registration";
	        }
	        try {
	            userService.register(user);
	        }catch (Exception e){
	            bindingResult.rejectValue("email", "user.email","An account already exists for this email.");
	            model.addAttribute("user", user);
	            return "users/registration";
	        }
        return "redirect:/user/list";
	}
	
	
	
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") int id, Model model) throws Exception {
		User theUser = userService.findById(id);
		model.addAttribute("user" , theUser);
		return "users/userForm";
	}
	
	@PostMapping("/save")
	public String addUser(@ModelAttribute("user") User user) throws Exception {
		userService.save(user);
		return "redirect:/user/list";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId") int id) throws Exception {
		User target = userService.findById(id);
		if(target == null) {
			throw new Exception("no such target");
		}
		
		userService.deleteById(id);
		
		return "redirect:/user/list";
		
	}
	
	
	
	
	
	
	
}
