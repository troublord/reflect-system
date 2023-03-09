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

import com.reflect.demo.entity.Activity;
import com.reflect.demo.service.ActivityService;

@Controller
@RequestMapping("/activities")
public class ActivityController {
	
	@Autowired
    private ActivityService activityService;
	

    @GetMapping("/{activityId}")
    public String getActivityById(@PathVariable Long activityId, Model model) {
        Activity activity = activityService.getActivityById(activityId);
        model.addAttribute("activity", activity);
        return "activity";//
    }

    @GetMapping("/")
    public String getAllActivities(Model model) {
        List<Activity> activities = activityService.getAllActivities();
        model.addAttribute("activities", activities);
        return "activities";
    }

    @GetMapping("/new")
    public String createActivity(Model model) {
        model.addAttribute("activity", new Activity());
        return "createActivity";
    }

    @PostMapping("/")
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

    @PostMapping("/{activityId}")
    public String updateActivity(@ModelAttribute("activity") Activity activity) {
        activityService.updateActivity(activity);
        return "redirect:/activities/";
    }

    @GetMapping("/{activityId}/delete")
    public String deleteActivity(@PathVariable Long activityId) {
        activityService.deleteActivityById(activityId);
        return "redirect:/activities/";
    }
	
}

