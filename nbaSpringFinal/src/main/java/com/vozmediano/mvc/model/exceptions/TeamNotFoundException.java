package com.vozmediano.mvc.model.exceptions;

public class TeamNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TeamNotFoundException() {
		super();
	}
	
	public TeamNotFoundException(String id) {
		super("Team not found: " + id);
	}
	
}
