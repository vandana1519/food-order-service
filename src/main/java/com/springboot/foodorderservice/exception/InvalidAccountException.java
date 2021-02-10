package com.springboot.foodorderservice.exception;

public class InvalidAccountException extends Exception {

	private static final long serialVersionUID = 4073428868108678532L;

	public InvalidAccountException(String message) {
		super(message);
	}
	
}
