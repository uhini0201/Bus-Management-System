package com.nri.busmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address{

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long addressId;
	private String busStopName;
	private String streetName;
	private String landMark;
	private String pinCode;
	public Address() {
		super();
	}
	public Address(Long addressId, String busStopName, String streetName, String landMark, String pinCode) {
		super();
		this.addressId = addressId;
		this.busStopName = busStopName;
		this.streetName = streetName;
		this.landMark = landMark;
		this.pinCode = pinCode;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getBusStopName() {
		return busStopName;
	}
	public void setBusStopName(String busStopName) {
		this.busStopName = busStopName;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", busStopName=" + busStopName + ", streetName=" + streetName
				+ ", landMark=" + landMark + ", pinCode=" + pinCode + "]";
	}
	
}
