package com.felipe.springmongo1.services.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		//repassa a msg pra superclasse RunTimeException
		super(msg);
	}

}
