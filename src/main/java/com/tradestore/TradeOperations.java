package com.tradestore;

import com.tradestore.exception.TradeStoreException;
import com.tradestore.model.TradeDetail;

/*
 * Interface for supported TradeStore operation*/
public interface TradeOperations {

	public void addTrade(TradeDetail tradeDetail) throws TradeStoreException;

	public void updateTrade(TradeDetail tradeDetail);

	public boolean removeTrade(TradeDetail tradeDetail) throws TradeStoreException;

	public boolean isValidTradeStoreDetail(TradeDetail tradeDetail) throws TradeStoreException;
}
