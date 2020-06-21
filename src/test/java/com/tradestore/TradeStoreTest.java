package com.tradestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tradestore.exception.TradeStoreException;
import com.tradestore.model.TradeDetail;

public class TradeStoreTest {
	private TradeStore store;

	public TradeStoreTest() {
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
	public void testAddTradeInEmptyStore() throws TradeStoreException {
		// default time zone
		ZoneId defaultZoneId = ZoneId.systemDefault();

		// creating the instance of LocalDate using the day, month, year info
		LocalDate givenDate = LocalDate.of(2020, 07, 20);

		// local date + atStartOfDay() + default time zone + toInstant() = Date
		Date date = Date.from(givenDate.atStartOfDay(defaultZoneId).toInstant());

		TradeDetail tdetail = new TradeDetail("T1", 1, "CP-1", "B1", date);
		store.addTrade(tdetail);

		assertTrue(true);
	}

	@Test
	public void testAddMaturityDateLessThanToday() {

		// default time zone
		ZoneId defaultZoneId = ZoneId.systemDefault();

		// creating the instance of LocalDate using the day, month, year info
		LocalDate givenDate = LocalDate.of(2020, 05, 20);

		// local date + atStartOfDay() + default time zone + toInstant() = Date
		Date date = Date.from(givenDate.atStartOfDay(defaultZoneId).toInstant());

		TradeDetail tdetail = new TradeDetail("T2", 1, "CP-2", "B2", date);

		assertThrows(TradeStoreException.class, () -> {
			store.addTrade(tdetail);
		}, "Invalid TradeDeatil : Maturity Date is less than current date");
	}

	@Test
	public void testDuplicateTradeWithHireVersion_Override() throws TradeStoreException {
		// default time zone

		ZoneId defaultZoneId = ZoneId.systemDefault();

		// creating the instance of LocalDate using the day, month, year info
		LocalDate givenDate = LocalDate.of(2020, 07, 20);

		// local date + atStartOfDay() + default time zone + toInstant() = Date
		Date date = Date.from(givenDate.atStartOfDay(defaultZoneId).toInstant());

		TradeDetail tdetail_1 = new TradeDetail("T3", 3, "CP-3", "B3", date);

		TradeDetail tdetail_2 = new TradeDetail("T3", 4, "CP-3", "B3", date);
		store.addTrade(tdetail_1);

		store.addTrade(tdetail_2);
		// store size should be 1 one only and version should be 3
		Collection<TradeDetail> tradeList = store.getAllTradeDetails();
		List<TradeDetail> output = new ArrayList<TradeDetail>();
		tradeList.stream().filter(x -> {
			if (x.getCounterPartyID().equalsIgnoreCase(tdetail_2.getCounterPartyID())
					&& x.getBookID().equalsIgnoreCase(tdetail_2.getBookID())
					&& x.getTradeID().equalsIgnoreCase(tdetail_2.getTradeID())) {
				return true;
			} else {
				return false;
			}
		}).forEach(output::add);
		assertTrue(1 <= output.size());
		assertEquals(4, output.iterator().next().getVersion());
	}

}
