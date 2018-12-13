package org.ocean.dto;

import org.springframework.stereotype.Component;

@Component
public class ResourceNotFoundException extends Exception{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;
	private String Message;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	} 
	
	
	
}
