package com.tradestore.model;

import java.util.Date;

/*
 * POJO class for trade store details*/

public class TradeDetail {

	private String tradeID;
	private int version;
	private String counterPartyID;
	private String bookID;
	private Date maturityDate;
	private Date createdDate;
	private String expired;

	public TradeDetail(String tradeID, int version, String counterPartyID, String bookID, Date maturityDate) {
		this.tradeID = tradeID;
		this.version = version;
		this.counterPartyID = counterPartyID;
		this.bookID = bookID;
		this.maturityDate = maturityDate;
		this.createdDate = new Date(System.currentTimeMillis());
		this.expired = "N";
	}

	public String getTradeID() {
		return tradeID;
	}

	public void setTradeID(String tradeID) {
		this.tradeID = tradeID;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCounterPartyID() {
		return counterPartyID;
	}

	public void setCounterPartyID(String counterPartyID) {
		this.counterPartyID = counterPartyID;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String isExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

}
