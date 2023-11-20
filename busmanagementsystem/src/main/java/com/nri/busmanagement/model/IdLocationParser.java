package com.nri.busmanagement.model;

import java.util.List;

public class IdLocationParser {
	private List<String> pickUpPoints;
	private String routeName;
	public IdLocationParser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IdLocationParser(List<String> pickUpPoints, String routeName) {
		super();
		this.pickUpPoints = pickUpPoints;
		this.routeName = routeName;
	}
	public List<String> getPickUpPoints() {
		return pickUpPoints;
	}
	public void setPickUpPoints(List<String> pickUpPoints) {
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
		return "IdLocationParser [pickUpPoints=" + pickUpPoints + ", routeName=" + routeName + "]";
	}
}
