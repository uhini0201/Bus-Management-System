package com.nri.busmanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nri.busmanagement.model.Address;
import com.nri.busmanagement.repo.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepository addressRepo;
	
	@Override
	public Optional<Address> getAddress(Long aId){
		return this.addressRepo.findById((Long)aId);
	}

	@Override
	public List<Address> getAllAddress() {
		
		return this.addressRepo.findAll();
	}

	@Override
	public String addAddress(Address address) {
		if(address.getAddressId()!=null && address.getBusStopName()!=null && address.getLandMark()!=null) {
			this.addressRepo.save(address);
			return "Successfully added a address.";
		}
		return "Invalid Input!!";
		
	}

	@Override
	public String deleteAddress(Long aId) {
		if(aId!=null) {
			try {
				Address existingAddress=this.getAddress((long) aId).get();
				this.addressRepo.deleteById((long) existingAddress.getAddressId());
				return "Successfully Deleted.";
			}catch(Exception e) {
				return "Enter valid Address Id.";
			}
		}
		return "Invalid Input!!";
		
	}

	@Override
	public List<Address> getByBusStopName(String busStopName) {
		List<Address> lAddress=addressRepo.findAll();
		List<Address> resList=lAddress.stream()
				.filter(laddressobj-> laddressobj.getBusStopName().equals(busStopName))
				.collect(Collectors.toList());
		return resList;
	}

	@Override
	public String updateAddress(Address address) {
		Optional<Address> existingAddress=addressRepo.findById((long) address.getAddressId());
		if(existingAddress!=null) {
			existingAddress.get().setBusStopName(address.getBusStopName());
			existingAddress.get().setStreetName(address.getStreetName());
			existingAddress.get().setLandMark(address.getLandMark());
			existingAddress.get().setPinCode(address.getPinCode());
			
			addressRepo.save(existingAddress.get());
			return "Record got updated.";
		}
		return "Address does not exist";
	}
	
}
