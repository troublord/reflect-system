package com.reflect.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "actions")
public class Action {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "objective", length = 100)
    private String objective;

    @Column(name = "thoughts", columnDefinition = "TEXT")
    private String thoughts;

    @Column(name = "outcome", length = 255)
    private String outcome;

    @Column(name = "improvements", length = 500)
    private String improvements;
    
    //a 1 ~ 10 self rate data
    @Column(name = "satisfaction")
    private Integer satisfaction;

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    private Activity activity;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Timestamp  updatedAt;

    public Action() {
    	
    }

	public Action(Long id, String objective, String thoughts, String outcome, String improvements, Integer satisfaction,
			Activity activity, Date createdAt, Timestamp updatedAt) {
		this.id = id;
		this.objective = objective;
		this.thoughts = thoughts;
		this.outcome = outcome;
		this.improvements = improvements;
		this.satisfaction = satisfaction;
		this.activity = activity;
		this.createdAt = createdAt;
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

	public String getThoughts() {
		return thoughts;
	}

	public void setThoughts(String thoughts) {
		this.thoughts = thoughts;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getImprovements() {
		return improvements;
	}

	public void setImprovements(String improvements) {
		this.improvements = improvements;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}

	@Override
	public String toString() {
		return "Action [id=" + id + ", objective=" + objective + ", thoughts=" + thoughts + ", outcome=" + outcome
				+ ", improvements=" + improvements + ", satisfaction=" + satisfaction + ", activity=" + activity
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

    
}
