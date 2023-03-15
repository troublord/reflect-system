package com.reflect.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.reflect.demo.entity.Activity;
import com.reflect.demo.entity.User;
import com.reflect.demo.service.ActivityService;
import com.reflect.demo.service.UserService;

@Controller
@RequestMapping("/activities")
public class ActivityController {
	
	@Autowired
    private ActivityService activityService;
	

    @GetMapping("/{activityId}")
    public String getActivityById(@PathVariable Long activityId, Model model) {
        Activity activity = activityService.getActivityById(activityId);
        model.addAttribute("activity", activity);
        return "activity";
    }

    @GetMapping("/")
    public String getAllActivities(Model model) {
    	//all activities
        List<Activity> activities = activityService.getAllActivities();
        model.addAttribute("activities", activities);
        
        //new activity
        Activity activityForCreate = new Activity();
        model.addAttribute("newActivity",activityForCreate);
        //update activity
        Activity activityForUpdate = new Activity();
        model.addAttribute("updateActivity",activityForUpdate);
        return "activities/showActivities.html";
    }

    @GetMapping("/new")
    public String createActivity(Model model) {
        model.addAttribute("activity", new Activity());
        return "createActivity";
    }

    @PostMapping("/save")
    public String saveActivity(@ModelAttribute("activity") Activity activity) {    	
    	activityService.saveActivity(activity);
        return "redirect:/activities/";
    }

    
    @GetMapping("/{activityId}/edit")
    public String editActivity(@PathVariable Long activityId, Model model) {
        Activity activity = activityService.getActivityById(activityId);
        model.addAttribute("activity", activity);
        return "editActivity";
    }

    @PostMapping("/update")
    public String updateActivity(@ModelAttribute("activity") Activity activity) {
        activityService.updateActivity(activity);
        return "redirect:/activities/";
    }

    @GetMapping("/{activityId}/delete")
    public String deleteActivity(@PathVariable Long activityId) {
        activityService.deleteActivityById(activityId);
        return "redirect:/activities/";
    }
	
//    @GetMapping("/deleteUser")
//	public String deleteUser(@RequestParam("userId") int id) throws Exception {
//		User target = userService.findById(id);
//		if(target == null) {
//			throw new Exception("no such target");
//		}
//		
//		userService.deleteById(id);
//		
//		return "redirect:/user/list";
//		
//	}
    
}

