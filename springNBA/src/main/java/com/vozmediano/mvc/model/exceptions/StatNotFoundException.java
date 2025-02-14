package com.vozmediano.mvc.model.exceptions;

public class StatNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatNotFoundException() {
		super();
	}
	
	public StatNotFoundException(String id) {
		super("Stat not found: " + id);
	}
	
}
