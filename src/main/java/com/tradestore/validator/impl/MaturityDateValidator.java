package com.tradestore.validator.impl;

import java.util.Date;

import com.tradestore.exception.TradeStoreException;
import com.tradestore.model.TradeDetail;
import com.tradestore.validator.Validation;

public class MaturityDateValidator implements Validation {

	@Override
	public boolean isValidTrade(TradeDetail tradeDetail) throws TradeStoreException {
		Date currentDate = new Date(System.currentTimeMillis());
		if (tradeDetail.getMaturityDate().before(currentDate)) {
			throw new TradeStoreException("Invalid TradeDeatil : Maturity Date is less than current date");
		}
		return true;
	}

}
