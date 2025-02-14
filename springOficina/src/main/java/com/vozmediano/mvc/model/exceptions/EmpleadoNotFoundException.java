package com.vozmediano.mvc.model.exceptions;

public class EmpleadoNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmpleadoNotFoundException() {
		super();
	}
	
	public EmpleadoNotFoundException(String msg) {
		super(msg);
	}
	
	public EmpleadoNotFoundException(int id) {
		super("Empleado not found " + id);
	}
}
