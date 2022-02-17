package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	
	private String userId;
	
	@Id
	private String username;
	
	private long createdTimeStamp;
	
	//@OneToMany(mappedBy = "userId")
	//private List<Tweet> tweetId ;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	public void setCreatedTimeStamp(long createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	
	
	
	
	
	
	

}
