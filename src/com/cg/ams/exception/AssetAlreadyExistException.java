package com.cg.ams.exception;

public class AssetAlreadyExistException extends Exception {
	@Override
public  String getMessage() {
	return "Asset Already Exist";
}
}
