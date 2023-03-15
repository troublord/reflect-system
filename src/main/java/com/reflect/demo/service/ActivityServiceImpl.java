package com.reflect.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reflect.demo.dao.ActivityDao;
import com.reflect.demo.entity.Activity;
import com.reflect.demo.entity.User;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
    private ActivityDao activityDao;
	@Autowired
	private UserService userService;
	
	@Override
	@Transactional
	public Activity getActivityById(Long activityId) {
		Optional<Activity> activityOpt = activityDao.findById(activityId);

        if (activityOpt.isPresent()) {
            return activityOpt.get();
        } else {
            // handle the case where no activity with the specified ID exists
            return null;
        }
	}

	@Override
	@Transactional
	public List<Activity> getAllActivities() {
		return activityDao.findAll();
	}

	@Override
	@Transactional
	public Activity saveActivity(Activity activity) {
		User theUser = null;
		try {
			theUser = userService.findById(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	activity.setUser(theUser);
		return activityDao.save(activity);

	}

	@Override
	@Transactional
	public Activity updateActivity(Activity activity) {
		Optional<Activity> activityOpt = activityDao.findById(activity.getId());
		
        if (activityOpt.isPresent()) {
        	Activity beforeMergeActivity = activityOpt.get();
        	activity.setUser(beforeMergeActivity.getUser());
        	activity.setCreatedAt(beforeMergeActivity.getCreatedAt());
        } else {
            // handle the case where no activity with the specified ID exists
        }
		 return activityDao.update(activity);

	}

	@Override
	@Transactional
	public void deleteActivityById(Long activityId) {
		Optional<Activity> activityOpt = activityDao.findById(activityId);

        if (activityOpt.isPresent()) {
        	activityDao.delete(activityOpt.get());
        } else {
            // handle the case where no activity with the specified ID exists
        }
		
	}
	
	public String formatLocalDateTime(LocalDateTime dateTime) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    return dateTime.format(formatter);
	}
	

}
