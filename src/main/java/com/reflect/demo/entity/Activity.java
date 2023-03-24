package com.reflect.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;
    
    @Column(name = "user_username",nullable = false)
    private String userUsername;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDate updatedAt;
    
    

    public Activity() {}
    
	public Activity(Long id, String name, String description, String status,String userUsername,
			LocalDate createdAt, LocalDate updatedAt) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.userUsername = userUsername;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	 // getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUserUsername() {
        return userUsername;
    }
    
    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	 @PrePersist
	 public void prePersist() {
	    LocalDate now = LocalDate.now();
	    this.createdAt = now;
	    this.updatedAt = now;
	    if(this.status == null) {
	    	this.status = "Inactive";
	    } 
	}

	@PreUpdate
	public void preUpdate() {
	    this.updatedAt = LocalDate.now();
	    if(this.status == null) {
	    	this.status = "Inactive";
	    } 
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", userUsername=" + userUsername + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}

