package com.cg.ams.exception;

public class NoSuchAssetException extends Exception{

	@Override
	public  String getMessage() {
		return "Asset not available";
	}
}
