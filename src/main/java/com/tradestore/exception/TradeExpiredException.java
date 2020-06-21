package com.tradestore.exception;

import java.util.Date;

/**
 * Custom Exception for expired trade details
 */
public class TradeExpiredException extends TradeStoreException {

	private static final long serialVersionUID = 1L;
	private Date expiredOn;

	public TradeExpiredException(String errorMsg) {
		new Exception(errorMsg);
	}
	
	public TradeExpiredException(Date expiredOn) {
		this.expiredOn = expiredOn;
	}

	@Override
	public String toString() {
		return "Trade details has expired on : " + expiredOn;
	}

}
