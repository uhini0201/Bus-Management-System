package com.nri.busmanagement.model;

public enum Status {

	BOOKED,WAITLIST,CANCELLED;
	
	@Override
	public String toString() {
		switch (this) {
		case BOOKED:	return "BOOKED";
		case WAITLIST: return "WAITLIST";
		default: return "CANCELLED";
		}
	
	}
}
