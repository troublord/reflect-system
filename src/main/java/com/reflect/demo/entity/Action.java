package com.reflect.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "actions")
public class Action {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	@Column(name = "title",length = 100)
	private String title;
	
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


    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDateTime  updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany
    @JoinTable(name = "map_action_tag", 
               joinColumns = @JoinColumn(name = "action_id"), 
               inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();
    
    @ManyToMany(mappedBy = "actions")
    private Set<Goal> goals = new HashSet<>();

    public Action() {
    	
    }
    
    
    
	public Action(Long id,String title, String objective, String thoughts, String outcome, String improvements, Integer satisfaction,
			LocalDate createdAt, LocalDateTime updatedAt, User user, Set<Tag> tags, Set<Goal> goals) {
		this.id = id;
		this.title = title;
		this.objective = objective;
		this.thoughts = thoughts;
		this.outcome = outcome;
		this.improvements = improvements;
		this.satisfaction = satisfaction;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
		this.tags = tags;
		this.goals = goals;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Goal> getGoals() {
		return goals;
	}

	public void setGoals(Set<Goal> goals) {
		this.goals = goals;
	}



	@Override
	public String toString() {
		return "Action [id=" + id + ", title=" + title + ", objective=" + objective + ", thoughts=" + thoughts
				+ ", outcome=" + outcome + ", improvements=" + improvements + ", satisfaction=" + satisfaction
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", user=" + user + ", tags=" + tags
				+ ", goals=" + goals + "]";
	}
    
    
    
    
    
}
