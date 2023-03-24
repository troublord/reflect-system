package com.reflect.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
    private String username;
    private String password;
    private boolean enabled;
    
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nonKeyId;
    
    public User() {
    	
    }
    
    
    public User(String username, String password, boolean enabled, Long nonKeyId) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.nonKeyId = nonKeyId;
	}


	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


	public Long getNonKeyId() {
		return nonKeyId;
	}


	public void setNonKeyId(Long nonKeyId) {
		this.nonKeyId = nonKeyId;
	}
    
    
}
