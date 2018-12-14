package org.ocean.handler;

import org.ocean.dto.ResponseMessage;
import org.ocean.dto.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javassist.NotFoundException;

@ControllerAdvice

public class GenericExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseMessage> handleResourceNotFoundException(ResourceNotFoundException ex) throws Exception
	{
		ResponseMessage error = new ResponseMessage();
		error.setCode(HttpStatus.NOT_FOUND.value());
		error.setMessage("The resource does not exist");
		return new ResponseEntity<ResponseMessage>(error,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseMessage> handleNotFoundException(ResourceNotFoundException ex) throws Exception
	{
		ResponseMessage error = new ResponseMessage();
		error.setCode(HttpStatus.NOT_FOUND.value());
		error.setMessage("The resource does not exist");
		return new ResponseEntity<ResponseMessage>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> handleAllException(Exception ex) throws Exception
	{
		ResponseMessage error = new ResponseMessage();
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(ex.getMessage());
		
		return new ResponseEntity<ResponseMessage>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	
}
