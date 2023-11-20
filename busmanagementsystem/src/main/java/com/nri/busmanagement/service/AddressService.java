package com.nri.busmanagement.service;

import java.util.Optional;
import java.util.List;

import com.nri.busmanagement.model.Address;


public interface AddressService {

	public Optional<Address> getAddress(Long aId);
	public List<Address> getByBusStopName(String busStopName);
	public List<Address> getAllAddress();
	public String addAddress(Address address);
	public String deleteAddress(Long aId);
	public String updateAddress(Address address);
}
