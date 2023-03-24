package com.reflect.demo.instance;

import java.sql.Timestamp;


public class OverviewAction {
	private Long id;
	private String objective;
	private Integer satisfaction;
	private String activityName;
	private Timestamp updatedAt;

	public OverviewAction(Long id, String objective, Integer satisfaction, String activityName, Timestamp updatedAt) {
		this.id = id;
		this.objective = objective;
		this.satisfaction = satisfaction;
		this.activityName = activityName;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public Integer getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
}
