package com.nri.busmanagement.service;

import java.util.List;

import com.nri.busmanagement.model.Booking;
import com.nri.busmanagement.model.Bus;
import com.nri.busmanagement.model.WaitingList;

public interface WaitingService {

	public List<WaitingList> viewAllWaiting();
	public WaitingList viewByBus(Bus bus);
	public String addWaiting(WaitingList waiting);
	public String updateWaiting(WaitingList waitingList);
	public String removeWaiting(Bus bus);
	public void getFirstBooking(Bus bus);
	
}
