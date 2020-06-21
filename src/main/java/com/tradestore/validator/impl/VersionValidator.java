package com.tradestore.validator.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tradestore.TradeStore;
import com.tradestore.exception.TradeRejectedException;
import com.tradestore.exception.TradeStoreException;
import com.tradestore.model.TradeDetail;
import com.tradestore.validator.Validation;

/*
 * Trade version validator validating for correct version of trade */

public class VersionValidator implements Validation {

	private boolean isValid(Collection<TradeDetail> allTrades, TradeDetail tradeDetail)
			throws TradeStoreException {
		if(allTrades.size()==0) {
			return true;
		}
		List<TradeDetail> filteredData = new ArrayList<TradeDetail>();
		allTrades.stream().filter(x -> {
			if (x.getCounterPartyID().equalsIgnoreCase(tradeDetail.getCounterPartyID())
					&& x.getBookID().equalsIgnoreCase(tradeDetail.getBookID()) && x.getTradeID() == tradeDetail.getTradeID()) {
				return true;
			} else {
				return false;
			}
		}).forEach(filteredData::add);
		
		for(TradeDetail td: filteredData) {
			if(tradeDetail.getVersion() > td.getVersion()) {
				return true; // This will enable overwrite of the value in map
			}else {
				throw new TradeRejectedException(tradeDetail.getVersion());
			}
		}
		return false;
	}

	@Override
	public boolean isValidTrade(TradeDetail tradeDetail) throws TradeStoreException {
		return isValid(TradeStore.getAllTradeDetails(), tradeDetail);
	}

}
