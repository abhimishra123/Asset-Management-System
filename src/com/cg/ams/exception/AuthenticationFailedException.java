package com.cg.ams.exception;

public class AuthenticationFailedException extends Exception  {
	
	@Override
	public  String getMessage() {
		return "Please enter valid username and password";
	}
	
}
