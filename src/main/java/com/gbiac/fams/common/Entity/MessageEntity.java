package com.gbiac.fams.common.Entity;

/**
 * 用于
 * @author Mchen
 *
 */
public class MessageEntity {
	
	public static final String ALERT = "1";
	
	public static final String MESSAGE = "2";

	private String type;
	
	private String message;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
