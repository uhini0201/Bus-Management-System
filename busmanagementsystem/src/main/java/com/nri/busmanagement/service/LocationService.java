package com.nri.busmanagement.service;

import java.util.List;
import java.util.Optional;

import com.nri.busmanagement.model.Location;

public interface LocationService {
	
	public String addLocation(Location location);
	public String updateLocation(Location location);
	public Optional<Location> getLocation(Long locationId);
	public List<Location> getLocation(String busStop);
	public List<Location> getLocationByArrivalAndDepartureTime(Double arrivalTime,Double departureTime);
	public List<Location> getAllLocations();
	public String deleteLocation(Long locationId);

}
