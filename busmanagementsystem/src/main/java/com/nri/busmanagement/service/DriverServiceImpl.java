package com.nri.busmanagement.service;

import java.util.List;
import java.util.NoSuchElementException;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nri.busmanagement.model.Driver;
import com.nri.busmanagement.repo.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository driverRepo;

	private List<Driver> driverList;
	private List<Driver> resultList;

	@Override
	public List<Driver> getAllDriver() {
		return this.driverRepo.findAll();
	}

	@Override
	public Optional<Driver> getDriverById(String id) {
		return this.driverRepo.findById(id);
	}

	@Override
	public List<Driver> getDriverByName(String name) {
		driverList = this.getAllDriver();
		resultList = null;

		if (name!=null) {
			resultList = driverList.stream().filter(driver -> driver.getDriverName().equals(name))
					.collect(Collectors.toList());
		}
		return resultList;
	}

	@Override
	public String addDriver(Driver driver) {
		if (driver.getDriverName()!=null && driver.getDriverPhone()!=null
				&& driver.getDriverEmail()!=null && driver.getDriverSal()!=null) {
			this.driverRepo.save(driver);
			return "Driver is added successfully";
		}
		return "Either one or multiple fields are missing! Please send a valid driver to add to the database!";
	}

	@Override
	public String updateDriver(Driver updatedDriver) {
		System.out.println("update");

		System.out.println(updatedDriver);
		if(updatedDriver!=null && updatedDriver.getDriverEmail()!=null) {
			try {
				Driver existingDriver = this.getDriverById(updatedDriver.getDriverEmail()).get();
				if(updatedDriver.getDriverName()!=null && 
				updatedDriver.getDriverPhone()!=null && updatedDriver.getDriverEmail()!=null && 
				updatedDriver.getDriverSal()!=null) {
					

					existingDriver.setDriverName(updatedDriver.getDriverName());
					existingDriver.setDriverPhone(updatedDriver.getDriverPhone());
					existingDriver.setDriverSal(updatedDriver.getDriverSal());
					existingDriver.setDriverEmail(updatedDriver.getDriverEmail());
					System.out.println(existingDriver);
					
					this.driverRepo.save(existingDriver);
					return "Successfully updated the driver";
					
				}else {
					return "Either one or multiple feilds are missing";
				}
			} catch(NoSuchElementException ex) {
				return "No driver found with this ID";
			}
		}
		return "Either or both the updatedDriver or driverId is empty or null";
	}

	@Override
	public String deleteDriver(String id) {

		if (id!=null) {
			try {
				Driver existingDriver = this.driverRepo.findById(id).get();
				this.driverRepo.deleteById(existingDriver.getDriverEmail());
				return "Successfully Deleted.";
			} catch (NoSuchElementException e) {
				return "Enter valid Driver Id.";
			}
		}
		return "Invalid Input!!";
	}

}
