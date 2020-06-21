package com.tradestore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tradestore.exception.IncorrectVersionException;
import com.tradestore.exception.TradeExpiredException;
import com.tradestore.exception.TradeRejectedException;
import com.tradestore.exception.TradeStoreException;
import com.tradestore.model.TradeDetail;
import com.tradestore.validator.Validation;
import com.tradestore.validator.impl.MaturityDateValidator;
import com.tradestore.validator.impl.VersionValidator;

public class TradeStore implements TradeOperations {
    Logger logger = LoggerFactory.getLogger(TradeStore.class);

	private static Map<String, TradeDetail> store;
	private static List<Validation> validators;
	static {
		init();
	}
	
	public TradeStore() {
		init();
	}
	
	public static void init() {
		store = new ConcurrentHashMap<String, TradeDetail>();
		validators = new ArrayList<Validation>();
		registerValidators();
	}

	public static void init(int initialCapacityOfStore) {
		store = new ConcurrentHashMap<String, TradeDetail>(initialCapacityOfStore);
	}
	
	public static void clear() {
		store.clear();
	}
	public static void registerValidator(Validation validator) {
		validators.add(validator);
	}

	private static void registerValidators() {
		Validation maturityValidator = new MaturityDateValidator();
		Validation versionValidator = new VersionValidator();
		registerValidator(maturityValidator);
		registerValidator(versionValidator);
	}
	
	@Override
	public void updateTrade(TradeDetail tradeDetail) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean removeTrade(TradeDetail tradeDetail) throws TradeStoreException {
		// TODO Auto-generated method stub
		return false;
	}

	public static Collection<TradeDetail> getAllTradeDetails() {
		return store.values();
	}

	@Override
	public boolean isValidTradeStoreDetail(TradeDetail tradeDetail) throws TradeStoreException {
		boolean retVal = false;
		for (Validation validator : validators) {
			boolean validTrade;
			try {
				validTrade = validator.isValidTrade(tradeDetail);
				retVal = retVal && validTrade;
			} catch (TradeStoreException e) {
				logger.error("Error Processing Trade Detail : " + e.getMessage());
				throw e;
			}
		}
		return true;
	}

	@Override
	public void addTrade(TradeDetail tradeDetail) throws TradeStoreException {
		logger.info("Adding Trade Detail");
		try {
			if (isValidTradeStoreDetail(tradeDetail)) {
				store.put(tradeDetail.getTradeID(), tradeDetail);
				logger.info("Added Trade Detail Successfully");
			}
		} catch (TradeRejectedException | IncorrectVersionException | TradeExpiredException ex) {
			logger.error("Error Processing Trade Detail : " + ex.getMessage());
			throw ex;
		} catch (TradeStoreException ex) {
			logger.error("Error Processing Trade Detail : " + ex.getMessage());
			throw ex;
		}
	}

}
