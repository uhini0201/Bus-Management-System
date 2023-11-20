package com.nri.busmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nri.busmanagement.model.Driver;
import com.nri.busmanagement.service.DriverService;

@RestController
@RequestMapping("/configDriver")
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	
	@GetMapping(path="/viewAllDrivers")
	public List<Driver> getAllDriver(){
		return this.driverService.getAllDriver();
	}
	
	@GetMapping(path="/viewDriver/{id}")
	public Optional<Driver> getById(@PathVariable("id") String driverId) {
		return this.driverService.getDriverById(driverId);
	}
	
	@GetMapping(path="/viewDriverByName/{name}")
	public List<Driver> getDriverByName(@PathVariable("name") String driverName){
		return this.driverService.getDriverByName(driverName);
	}
	
	@PostMapping(path="/addDriver",consumes = "application/json")
	public String addDriver(@RequestBody Driver driver) {
		return this.driverService.addDriver(driver);
		
	}
	
	@PutMapping(path="/updateDriver",consumes="application/json")
	public String updateDriver(@RequestBody Driver driver) {
		return this.driverService.updateDriver(driver);
		
	}
	
	@DeleteMapping("/deleteDriver/{id}")
	public String deleteDriver(@PathVariable String id) {
		return this.driverService.deleteDriver(id);
		
	}
	
	
}
