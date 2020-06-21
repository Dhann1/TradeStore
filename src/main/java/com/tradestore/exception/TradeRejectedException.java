package com.tradestore.exception;


/**
 * Custom Exception for expired trade details
 */
public class TradeRejectedException extends TradeStoreException {

	private static final long serialVersionUID = 1L;
	private int version;

	public TradeRejectedException(String errorMsg) {
		new Exception(errorMsg);
	}
	
	public TradeRejectedException(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Trade rejected due to version received is lower than existing. the incorrect lower version is : " + version;
	}

}
