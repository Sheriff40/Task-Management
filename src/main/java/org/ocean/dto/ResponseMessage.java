package org.ocean.dto;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessage {

	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ResponseMessage [code=" + code + ", message=" + message + "]";
	}
	
	
	
	
	
}
