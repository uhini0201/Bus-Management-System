package com.nri.busmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nri.busmanagement.model.Bus;
import com.nri.busmanagement.model.Employee;
import com.nri.busmanagement.repo.BusRepository;

@Service
public class BusServiceImpl implements BusService{
	
	@Autowired
	private BusRepository busRepo;

	@Override
	public List<Bus> getAllBuses() {
		
		return busRepo.findAll();
	}

	@Override
	public Optional<Bus> getABusById(Long busId) {
		
		return busRepo.findById(busId);
	}

	@Override
	public void addBus(Bus bus) {
		
		this.busRepo.save(bus);
		
	}

	@Override
	public void updateBus(Bus updatedBus, Long busId) {
		
		if (updatedBus==null && busId==null) {
			// Employee Object and Employee ID cannot be NULL
			return;
		}
		
		// Fetch Employee by ID
		//! Issue: employee could be NULL, handling required
		Optional<Bus> existingBus = this.getABusById(busId);
		
		// Update Bus's numberPlate
		if (updatedBus.getBusNumberPlate() != null) {
			existingBus.get().setBusNumberPlate(updatedBus.getBusNumberPlate());
		}
		
		// Update Bus's capacity
		if (updatedBus.getBusCapacity() != null) {
			existingBus.get().setBusCapacity(updatedBus.getBusCapacity());
		}
		busRepo.save(updatedBus);
	}

	@Override
	public void deleteBusById(Long busId) {
		
		busRepo.deleteById(busId);
		
	}

}
