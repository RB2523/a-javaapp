package com.example.response;

public class UserResponse implements Response {

	private String userId;
	private long cretedTimeStamp;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getCretedTimeStamp() {
		return cretedTimeStamp;
	}
	public void setCretedTimeStamp(long cretedTimeStamp) {
		this.cretedTimeStamp = cretedTimeStamp;
	}

	
	
}
