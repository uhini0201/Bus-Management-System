package com.nri.busmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long busId;
	
	private String busNumberPlate;
	private Integer busCapacity;
	private Integer bookedSeats;
	private Integer remainingSeats;

	public Bus() {
		super();
	}

	public Bus(Long busId, String busNumberPlate, Integer busCapacity, Integer bookedSeats, Integer remainingSeats) {
		super();
		this.busId = busId;
		this.busNumberPlate = busNumberPlate;
		this.busCapacity = busCapacity;
		this.bookedSeats = bookedSeats;
		this.remainingSeats = remainingSeats;
	}

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
	}

	public String getBusNumberPlate() {
		return busNumberPlate;
	}

	public void setBusNumberPlate(String busNumberPlate) {
		this.busNumberPlate = busNumberPlate;
	}

	public Integer getBusCapacity() {
		return busCapacity;
	}

	public void setBusCapacity(Integer busCapacity) {
		this.busCapacity = busCapacity;
	}

	public Integer getBookedSeats() {
		return bookedSeats;
	}

	public void setBookedSeats(Integer bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	public Integer getRemainingSeats() {
		return remainingSeats;
	}

	public void setRemainingSeats(Integer remainingSeats) {
		this.remainingSeats = remainingSeats;
	}

	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", busNumberPlate=" + busNumberPlate + ", busCapacity=" + busCapacity
				+ ", bookedSeats=" + bookedSeats + ", remainingSeats=" + remainingSeats + "]";
	}

}
