package com.example.response;

import java.util.List;

import com.example.entity.Tweet;

public class TimeTweetResponse implements Response {

	private int countOfTweet;
	private List<Tweet> listOfTweet;
	
	public int getCountOfTweet() {
		return countOfTweet;
	}
	public void setCountOfTweet(int countOfTweet) {
		this.countOfTweet = countOfTweet;
	}
	public List<Tweet> getListOfTweet() {
		return listOfTweet;
	}
	public void setListOfTweet(List<Tweet> listOfTweet) {
		this.listOfTweet = listOfTweet;
	}
	
	
	
	
	
}
