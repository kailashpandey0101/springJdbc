package com.imcs.spring.exceptions;

public class EmployeeNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String msg) {
		super(msg);
	}
}
