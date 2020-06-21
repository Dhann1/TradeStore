package com.tradestore;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;

import com.tradestore.exception.TradeStoreException;
import com.tradestore.model.TradeDetail;
import com.tradestore.util.TradeExpiryCheckUtil;

/**
 * Trade Store Main class 
 *
 */
public class TradeStoreApp
{
    public static void main( String[] args ) throws TradeStoreException //throws TradeStoreException
    {
    		BasicConfigurator.configure();

    		//Adding sample data on store
    		TradeStore store = new TradeStore();
    		
    		//default time zone
    		ZoneId defaultZoneId = ZoneId.systemDefault();
    		
    		//creating the instance of LocalDate using the day, month, year info
    		LocalDate givenDate = LocalDate.of(2020,07,20);
    	        
    	    //local date + atStartOfDay() + default time zone + toInstant() = Date
    		Date date = Date.from(givenDate.atStartOfDay(defaultZoneId).toInstant());

    		TradeDetail tdetail = new TradeDetail("T1",1,"CP-1","B1",date);
    		
    		store.addTrade(tdetail);
    		
    		//Start Expire checker job every 10 minutes
    		TradeExpiryCheckUtil.startExpireCheckerJob(store,1);

    		System.out.println( "Sample Trade data added to store." );
    }
}
