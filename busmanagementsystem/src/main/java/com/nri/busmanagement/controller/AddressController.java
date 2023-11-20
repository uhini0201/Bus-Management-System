package com.nri.busmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nri.busmanagement.model.Address;
import com.nri.busmanagement.service.AddressServiceImpl;

@RestController
@RequestMapping("/admin")
public class AddressController {

	@Autowired
	private AddressServiceImpl addressService;
	
	@GetMapping("/view/{id}")
	public Address viewAddressById(@PathVariable Long id) {
		return this.addressService.getAddress(id).get();
	}
	@GetMapping("/viewAll")
	public List<Address> viewAllAddress(){
		return this.addressService.getAllAddress();
	}
	@PostMapping("/create")
	public String createAddress(@RequestBody Address address) {
		return this.addressService.addAddress(address);
	}
	
	@PutMapping("/update")
	public String updateAddress(@RequestBody Address address) {
		return this.addressService.updateAddress(address);
	}
	
	@DeleteMapping("/remove")
	public String removeAddress(@RequestBody Address address) {
		Long aId=address.getAddressId();
		return this.addressService.deleteAddress(aId);
	}
	
}
