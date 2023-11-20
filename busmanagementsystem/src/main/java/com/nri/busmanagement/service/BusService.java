package com.nri.busmanagement.service;

import java.util.List;
import java.util.Optional;


import com.nri.busmanagement.model.Bus;


public interface BusService {

	//admin services
	public List<Bus> getAllBuses();

	public Optional<Bus> getABusById(Long busId);

	public void addBus(Bus bus);

	public void updateBus(Bus bus, Long busId);

	public void deleteBusById(Long busId);

}
