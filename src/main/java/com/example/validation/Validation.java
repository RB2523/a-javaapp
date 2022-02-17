package com.example.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;

public class Validation {

	public static boolean usernameValidator(String username) {
		if(username.length() >= 8  &&  username.length() <= 10)  return true;
		
		return false;
	}
	
	
	
	public static boolean TextValidator(String text) {
		if(text.length() >= 2  &&  text.length() <= 140)  return true;
		
		return false;
	}
	
	
	
	public static boolean DateValidator(String date) {
		
    	String[] dateParts = date.split("-");
		
		if(dateParts.length != 3) return false;
		
		String day = dateParts[0];
		String month = dateParts[1];
		String year = dateParts[2];
		
		if(day.length() != 2  || Integer.valueOf(day) > 31) return false;
		
		if(month.length() != 2  || Integer.valueOf(month) > 12) return false;
		
		if(year.length() != 4 ) return false;
		
		return true;
	}
}
