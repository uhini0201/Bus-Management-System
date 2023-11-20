package com.nri.busmanagement.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class WaitingList {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@OneToOne
	private Bus busobj;
	
	//List of booking id stored here
	@OneToMany
	private List<Booking> wailtList;
	
	public WaitingList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WaitingList(Long id, Bus busobj, List<Booking> wailtList) {
		super();
		this.id = id;
		this.busobj = busobj;
		this.wailtList = wailtList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Bus getBusobj() {
		return busobj;
	}

	public void setBusobj(Bus busobj) {
		this.busobj = busobj;
	}

	public List<Booking> getWailtList() {
		return wailtList;
	}

	public void setWailtList(List<Booking> wailtList) {
		this.wailtList = wailtList;
	}

	@Override
	public String toString() {
		return "WaitingList [id=" + id + ", busobj=" + busobj + ", wailtList=" + wailtList + "]";
	}
	
	
	
	
}
