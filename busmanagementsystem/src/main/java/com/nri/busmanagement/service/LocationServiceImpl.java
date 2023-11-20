package com.nri.busmanagement.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nri.busmanagement.model.Location;
import com.nri.busmanagement.repo.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService{
	
	@Autowired
	private LocationRepository locationRepo;
	
	@Autowired
	private AddressService addrServ;
	
	List<Location> listLocation;
	List<Location> resultLocation ;

	@Override
	public String addLocation(Location location) {
		this.addrServ.addAddress(location.getAddressobj());
		this.locationRepo.save(location);
		return "Successfully Added Location";
	}

	@Override
	public String updateLocation(Location updatedLocation) {
		if (updatedLocation!=null) {
			try {
				Location existingLocation = this.getLocation(updatedLocation.getLocationId()).get();
				if (updatedLocation.getLocationId()!=null && updatedLocation.getAddressobj()!=null && updatedLocation.getArrivalTime()!=null && updatedLocation.getDepartureTime()!=null) {
//					existingLocation.setLocationId(updatedLocation.getLocationId());
//					existingLocation.setAddressobj(updatedLocation.getAddressobj());
//					existingLocation.setArrivalTime(updatedLocation.getArrivalTime());
//					existingLocation.setDepartureTime(updatedLocation.getDepartureTime());
//					
//					this.locationRepo.save(existingLocation);
					
					this.addrServ.updateAddress(updatedLocation.getAddressobj());
					this.locationRepo.save(updatedLocation);

					return "Successfully updated the Location!";
				} else {
					return "Either one or multiple fields are missing!";
				}
			} catch (NoSuchElementException ex) {
				return "No location found with this Id";
			}
		}
		return "Either or both the updatedLocation or locationId is empty/null";
	}

	@Override
	public Optional<Location> getLocation(Long locationId) {
		return this.locationRepo.findById(locationId);
	}

	@Override
	public List<Location> getLocation(String busStop) {
		listLocation=this.getAllLocations();
		resultLocation= listLocation.stream()
			    .filter(location -> location.getAddressobj().getBusStopName().equals(busStop))
			    .collect(Collectors.toList());

		return resultLocation;
	}

	@Override
	public List<Location> getLocationByArrivalAndDepartureTime(Double arrivalTime,Double departureTime) {
		listLocation=this.getAllLocations();
		resultLocation=null;
		if(arrivalTime!=null && departureTime!=null)
			resultLocation=listLocation.stream().filter(location -> location.getDepartureTime()<=departureTime).collect(Collectors.toList());
		else if(departureTime!=null && arrivalTime!=null)
			resultLocation=listLocation.stream().filter(location -> location.getArrivalTime()>=arrivalTime).collect(Collectors.toList());
		else if(arrivalTime!=null && departureTime!=null) 
			resultLocation=listLocation.stream().filter(location -> location.getArrivalTime()>=arrivalTime &&  location.getDepartureTime()<=departureTime).collect(Collectors.toList());
		return resultLocation;
		
	}

	@Override
	public List<Location> getAllLocations() {
		List<Location> llist= this.locationRepo.findAll();
		System.out.println(llist);
		return llist;
	}

	@Override
	public String deleteLocation(Long locationId) {
		if (locationId!=null) {
			try {
				Location existingLocation = this.getLocation(locationId).get();
				this.locationRepo.deleteById(existingLocation.getLocationId());
				return "Successfully deleted the location!";
			}
			catch (NoSuchElementException ex) {
				return "Enter a valid locationId to delete the location";
			}
		}
		return "Please enter the locationId field";
	}

}
