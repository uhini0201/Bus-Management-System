package com.nri.busmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nri.busmanagement.model.Location;
import com.nri.busmanagement.repo.LocationRepository;


@RestController
public class TestController {
	
	@Autowired
	LocationRepository locRepo;
	
//	@GetMapping("/route")
//	public void getRoute() {
//		// The user will provide the location from which the locationId will be extracted
//		Location loc = locRepo.findById((int) 101).get();
//		
//		locRepo.getRouteFromLocation(loc.getAddressobj());
//	}
}
