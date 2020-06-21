package com.tradestore.exception;

/**
 * Custom Exception for incorrect trade version 
 */
public class IncorrectVersionException extends TradeStoreException {

	private static final long serialVersionUID = 1L;
	private String version;

	public IncorrectVersionException() {
		new Exception("Incorrect trade version provided");
	}
	
	public IncorrectVersionException(String version){
		this.version = version;
	}

	@Override
	public String toString() {
		return "Incorrect trade version provided : " + version;
	}

}
