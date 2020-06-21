package com.tradestore.util;

import java.util.Date;
import java.util.Timer;

import com.tradestore.TradeStore;

/*
 * This is a Utility class which help is checking the expiry of a Trade and set the expire accordingly */
public class TradeExpiryCheckUtil {
	private static long noOfTradeExpired;
	
	public static void setNoOfTradeMarkedExpiredInLastItr(long noOfTrade) {
		noOfTradeExpired = noOfTrade;
	}
	
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}
	
	public static void startExpireCheckerJob(TradeStore tradeStore,long intervalInMinutes) {
		TradeExpireChecker tradeExpChecker = new TradeExpireChecker(tradeStore);
		Timer t=new Timer();
	    t.scheduleAtFixedRate(tradeExpChecker, 0,intervalInMinutes*60*1000);
	}
	
}
