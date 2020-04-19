package com.cg.ams.exception;

public class InvalidDateException extends Exception {
	@Override
	public  String getMessage() {
		return "Please Enter valid date";
	}

}
