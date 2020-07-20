package com.springboot.budgetapp.exception;


public class ApplicationRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2194539696190462996L;

	public ApplicationRequestException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ApplicationRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
