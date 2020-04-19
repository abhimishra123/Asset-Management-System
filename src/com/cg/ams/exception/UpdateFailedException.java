package com.cg.ams.exception;

public class UpdateFailedException extends Exception {
    
	@Override
	public  String getMessage() {
		return "Update Operation Failed";
	}
}
