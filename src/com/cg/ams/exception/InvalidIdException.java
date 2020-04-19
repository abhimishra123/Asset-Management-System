package com.cg.ams.exception;

public class InvalidIdException extends Exception {
  
	@Override
	public  String getMessage() {
		return "Enter valid Employee Id";
	}
	
}
