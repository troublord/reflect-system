package com.reflect.demo.service;

import java.util.List;

import com.reflect.demo.entity.Activity;

public interface ActivityService {
	Activity getActivityById(Long activityId);

    List<Activity> getAllActivities();

    Activity saveActivity(Activity activity);

    Activity updateActivity(Activity activity);

    void deleteActivityById(Long activityId);
    
    
}
