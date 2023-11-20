package com.nri.busmanagement.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long locationId;

	@OneToOne(cascade = CascadeType.ALL)
	private Address addressobj;
	
	private Double arrivalTime;
	private Double departureTime;

	public Location() {
		super();
	}

	public Location(Long locationId, Address addressobj, Double arrivalTime, Double departureTime) {
		super();
		this.locationId = locationId;
		this.addressobj = addressobj;
		this.arrivalTime=arrivalTime;
		this.departureTime=departureTime;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Address getAddressobj() {
		return addressobj;
	}

	public void setAddressobj(Address addressobj) {
		this.addressobj = addressobj;
	}

	
	
	public Double getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Double getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Double departureTime) {
		this.departureTime = departureTime;
	}

	@Override
	public String toString(){
		return "Location [locationId=" + locationId + ", addressobj=" + addressobj + ", arrivalTime=" + arrivalTime
				+ ", departureTime=" + departureTime + "]";
	}
	
}
