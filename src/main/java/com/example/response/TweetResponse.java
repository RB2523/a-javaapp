package com.example.response;

public class TweetResponse implements Response {

	
	private String tweetId;
	private long cretedTimeStamp;
	
	public String getTweetId() {
		return tweetId;
	}
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	public long getCretedTimeStamp() {
		return cretedTimeStamp;
	}
	public void setCretedTimeStamp(long cretedTimeStamp) {
		this.cretedTimeStamp = cretedTimeStamp;
	}
	
	
	
}
