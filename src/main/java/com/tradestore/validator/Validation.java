package com.tradestore.validator;

import com.tradestore.exception.TradeStoreException;
import com.tradestore.model.TradeDetail;

/*
 * Trade validtion interface*/
public interface Validation {

	boolean isValidTrade(TradeDetail tradeDetail) throws TradeStoreException;

}
