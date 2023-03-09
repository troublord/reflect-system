package com.reflect.demo.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reflect.demo.dao.ActivityDao;
import com.reflect.demo.entity.Activity;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
    private ActivityDao activityDao;
	
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
	public void saveActivity(Activity activity) {
		activityDao.save(activity);

	}

	@Override
	@Transactional
	public void updateActivity(Activity activity) {
		 activityDao.update(activity);

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
	

}
