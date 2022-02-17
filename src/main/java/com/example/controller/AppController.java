package com.example.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.DateDTO;
import com.example.dto.TweetDTO;
import com.example.dto.UserDTO;
import com.example.entity.Tweet;
import com.example.entity.User;
import com.example.repository.TweetRepository;
import com.example.repository.UserRepository;
import com.example.response.DeleteTweetResponse;
import com.example.response.MessageResponse;
import com.example.response.Response;
import com.example.response.TimeTweetResponse;
import com.example.response.TweetResponse;
import com.example.response.UserResponse;
import com.example.utility.DeleteTweetResponseList;
import com.example.validation.Validation;

@RestController
@CrossOrigin("*")
public class AppController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TweetRepository tweetRepository;

	
	@PostMapping("/users")
	public ResponseEntity<Response> createUser(@RequestBody UserDTO userDTO) {
		
		String uniqueID = UUID.randomUUID().toString();
		
		Date date = new Date();
		long timeStamp = date.getTime() ;
		
		User user = new User();
		user.setUserId(uniqueID);
		user.setUsername(userDTO.getUsername());
		user.setCreatedTimeStamp(timeStamp);
		
	  if(!Validation.usernameValidator(user.getUsername())) {
		  
		  MessageResponse response = new MessageResponse();
		  response.setMessage("Name length must be between 8 - 10");
		  return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
	  }
	  
	  User result = this.userRepository.findByUsername(userDTO.getUsername());
		
		if(result != null) {
			MessageResponse response = new MessageResponse();
		    response.setMessage("username already exist");
			return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
		}
		
		this.userRepository.save(user);
		
		UserResponse response = new UserResponse();
		response.setUserId(uniqueID);
		response.setCretedTimeStamp(timeStamp);
		
		return ResponseEntity.ok(response); 
	}
	
	
	@PostMapping("/tweets")
	public ResponseEntity<Response> createTweet(@RequestBody TweetDTO tweetDTO) {
		
		if(!Validation.TextValidator(tweetDTO.getText())) {
			  MessageResponse response = new MessageResponse();
			  response.setMessage("text length must be between 2 - 140");
			  return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
		  }
		
		String uniqueID = UUID.randomUUID().toString();
		
		Date date = new Date();
		long timeStamp = date.getTime() ;
		
		User user = this.userRepository.findByUserId(tweetDTO.getUserId());
		
		if(user == null) {
			MessageResponse response = new MessageResponse();
		    response.setMessage("invalid id");
			return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
		}
		
		Tweet tweet = new Tweet();
		tweet.setTweetId(uniqueID);
		tweet.setText(tweetDTO.getText());
		tweet.setUsername(user);
		tweet.setCreatedTimeStamp(timeStamp);
		
		this.tweetRepository.save(tweet);
		
		TweetResponse response = new TweetResponse();
		response.setTweetId(uniqueID);
		response.setCretedTimeStamp(timeStamp);
		
		return ResponseEntity.ok(response); 
	}
	
	
	@PostMapping("/tweets/users/date")
	public ResponseEntity<Response> getTweetByDate(@RequestBody DateDTO dateDTO) throws ParseException {
		
		if( !Validation.DateValidator(dateDTO.getDate())  ) {
			MessageResponse response = new MessageResponse();
			response.setMessage("date must be in dd-MM-yyyy format");
			return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
		  }
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		Date d = dateFormat.parse(dateDTO.getDate());
		String time = String.valueOf( d.getTime() );
		
		List<Tweet> tweetList = this.tweetRepository.findTweetByUsernameANdTime(dateDTO.getUsername(), time);
		
		TimeTweetResponse response = new TimeTweetResponse();
		response.setCountOfTweet(tweetList.size());
		response.setListOfTweet(tweetList);
		
		return ResponseEntity.ok(response); 
	}
	
	
	
	@DeleteMapping("/users/tweets")
	public ResponseEntity<Response>  createTweet(@RequestBody UserDTO userDTO) {
		
		List<Tweet> tweetList = this.tweetRepository.findTweetByUsername(userDTO.getUsername());
		
		this.tweetRepository.deleteTweetsByUsername(userDTO.getUsername());
		
		DeleteTweetResponse response = new DeleteTweetResponse();
		response.setNoOfTweetDeleted(tweetList.size());
		
		List<DeleteTweetResponseList> deleteTweetResponseList = new ArrayList<>(); 
		
		for(int i=0;i<tweetList.size();i++) {
			DeleteTweetResponseList tweetDTO = new DeleteTweetResponseList();
			tweetDTO.setTweetId(tweetList.get(i).getTweetId());
			tweetDTO.setText(tweetList.get(i).getText());
			
			deleteTweetResponseList.add(tweetDTO);
		}
		
		
		response.setList(deleteTweetResponseList);
		
		return ResponseEntity.ok(response); 
	}

	
	
}
