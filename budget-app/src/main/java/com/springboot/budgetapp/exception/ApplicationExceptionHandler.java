package com.springboot.budgetapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(ApplicationRequestException.class)
	public ResponseEntity<Object> handlerUserException(ApplicationRequestException e){
		
		ApplicationException exeption = new ApplicationException(e.getMessage(),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(exeption,HttpStatus.BAD_REQUEST);
		
	}
}
