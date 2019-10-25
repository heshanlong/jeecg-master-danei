package com.gbiac.fams.common.Entity;

public class MessageType {
	
	private int type;
	private String message;
	public MessageType(int type,String message) {
		this.type = type;
		this.message = message;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
