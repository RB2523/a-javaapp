package com.example.response;

import java.util.List;

import com.example.utility.DeleteTweetResponseList;


public class DeleteTweetResponse implements Response {

	private int noOfTweetDeleted;
	private List<DeleteTweetResponseList> list;
	
	public int getNoOfTweetDeleted() {
		return noOfTweetDeleted;
	}
	public void setNoOfTweetDeleted(int noOfTweetDeleted) {
		this.noOfTweetDeleted = noOfTweetDeleted;
	}
	public List<DeleteTweetResponseList> getList() {
		return list;
	}
	public void setList(List<DeleteTweetResponseList> list) {
		this.list = list;
	}
	
	
	
	
}
