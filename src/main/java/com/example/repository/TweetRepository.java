package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, String> {

	@Transactional
	@Modifying
	@Query(value ="delete from tweet where username= :name" , nativeQuery = true)
	public void deleteTweetsByUsername(@Param("name") String name);
	
	@Query(value ="select * from tweet where username= :name" , nativeQuery = true)
	public List<Tweet> findTweetByUsername(@Param("name") String name);
	
	
	@Query(value ="select * from tweet where username= :name and created_time_stamp > :time" , nativeQuery = true)
	public List<Tweet> findTweetByUsernameANdTime(@Param("name") String name , @Param("time") String time);
}
