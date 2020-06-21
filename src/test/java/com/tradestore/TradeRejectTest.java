package com.tradestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tradestore.exception.TradeRejectedException;
import com.tradestore.exception.TradeStoreException;
import com.tradestore.model.TradeDetail;

public class TradeRejectTest {
	private TradeStore store;

	public TradeRejectTest() {
		store = new TradeStore();
	}

	@BeforeEach
	public void initializeTradeStore() {
		assertNotNull(store);
	}

	@AfterEach
	public void initializeTradeStoreWithinitialLimit() {
		store.clear();
		store = null;
		assertNull(store);
	}

	@Test
	public void testDuplicateTradeWithHireVersion_Reject() throws TradeStoreException {
		// default time zone

		ZoneId defaultZoneId = ZoneId.systemDefault();

		// creating the instance of LocalDate using the day, month, year info
		LocalDate givenDate = LocalDate.of(2020, 07, 20);

		// local date + atStartOfDay() + default time zone + toInstant() = Date
		Date date = Date.from(givenDate.atStartOfDay(defaultZoneId).toInstant());

		TradeDetail tdetail_1 = new TradeDetail("T4", 2, "CP-4", "B4", date);

		TradeDetail tdetail_2 = new TradeDetail("T4", 1, "CP-4", "B4", date);
		store.addTrade(tdetail_1); // First trade with Higher version

		assertThrows(TradeRejectedException.class, () -> {
			store.addTrade(tdetail_2);
		}); // Second trade with lower version , should get rejected

		// store size should be 1 one only
		assertEquals(1, store.getAllTradeDetails().size());

	}

}
