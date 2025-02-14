package com.vozmediano.mvc.model.exceptions;

public class PlayerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayerNotFoundException() {
		super();
	}
	
	public PlayerNotFoundException(int id) {
		super("Player not found: " + id);
	}
	
}
