package com.nri.busmanagement.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long routeId;
	
	private String routeName;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Location> pickUpPoints;	
	
	public Route() {
		super();
	}
	public Route(Long routeId, List<Location> pickUpPoints, String routeName) {
		super();
		this.routeId = routeId;
		this.pickUpPoints = pickUpPoints;
		this.routeName = routeName;
		
	}
	public Long getRouteId() {
		return routeId;
	}
	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}
	
	public List<Location> getPickUpPoints() {
		return pickUpPoints;
	}
	public void setPickUpPoints(List<Location> pickUpPoints) {
		this.pickUpPoints = pickUpPoints;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	
	
	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", pickUpPoints=" + pickUpPoints + ", routeName=" + routeName + "]";
	}
	

	
	
	
	
	
}
