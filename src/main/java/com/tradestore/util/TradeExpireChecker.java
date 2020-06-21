package com.tradestore.util;

import java.util.Collection;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tradestore.TradeStore;
import com.tradestore.model.TradeDetail;

public class TradeExpireChecker extends TimerTask {
	Logger logger = LoggerFactory.getLogger(TradeExpireChecker.class);
	private Collection<TradeDetail> tradeDetails;
	private TradeStore tradeStore;
	
	public TradeExpireChecker(TradeStore store){
		this.tradeStore = store;
		this.tradeDetails = tradeStore.getAllTradeDetails();
	}

	@Override
	public void run() {
		logger.info("Trade Expire Checker Execution started at Date : " + TradeExpiryCheckUtil.getCurrentDate());
		long noOfTradeExpired = this.tradeDetails.stream().filter(x -> {
			if(x.getMaturityDate().before(TradeExpiryCheckUtil.getCurrentDate())) {
				x.setExpired("Y");
				try {
					tradeStore.addTrade(x);
				}catch(Exception ex) {
					logger.error("Expire Falg Update failed : "+  ex.getMessage());	
				}
				return true;
			}else {
				return false;
			}
		}).count();
		logger.info("Trade Expire Checker Execution ended at Date : " + TradeExpiryCheckUtil.getCurrentDate());
		logger.info("No of trade marked expired in this cycle is : "+noOfTradeExpired);
		TradeExpiryCheckUtil.setNoOfTradeMarkedExpiredInLastItr(noOfTradeExpired);
	}

}
