package com.nri.busmanagement.service;

import java.util.List;
import java.util.Optional;

import com.nri.busmanagement.model.Driver;

public interface DriverService {
	public List<Driver> getAllDriver();
	public Optional<Driver> getDriverById(String id);
	public List<Driver> getDriverByName(String name);
	public String addDriver(Driver driver);
	public String updateDriver(Driver updatedDriver);
	public String deleteDriver(String id);
}
