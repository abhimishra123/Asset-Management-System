package com.cg.ams.exception;

public class UserAlreadyExistsException extends Exception {

	@Override
	public  String getMessage() {
		return "This user Is already exits";
	}
}
