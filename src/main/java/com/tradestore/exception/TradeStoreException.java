package com.tradestore.exception;

public class TradeStoreException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	
	public TradeStoreException() {
		
	}
	
	public TradeStoreException(String errorMsg){
		new Exception(errorMsg);
	}
	
}
