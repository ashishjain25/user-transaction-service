package com.assignment.usertransaction.exception;

public class TransactionException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public TransactionException(String message) {
		super(message);
	}

}
