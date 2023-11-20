package com.nri.busmanagement.model;

public class ScheduleParser {
	
	private String startDate;
	private String startTime;
	private String endTime; 
	private String fareAmount; 
	private String driverId; 
	private String condId; 
	private String busId; 
	private String routeId;
	
	public ScheduleParser() {
		super();
	}

	public ScheduleParser(String startDate, String startTime, String endTime, String fareAmount, String driverId,
			String condId, String busId, String routeId) {
		super();
		this.startDate = startDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.fareAmount = fareAmount;
		this.driverId = driverId;
		this.condId = condId;
		this.busId = busId;
		this.routeId = routeId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getFareAmount() {
		return fareAmount;
	}

	public void setFareAmount(String fareAmount) {
		this.fareAmount = fareAmount;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getCondId() {
		return condId;
	}

	public void setCondId(String condId) {
		this.condId = condId;
	}

	public Long getBusId() {
		return Long.parseLong(busId);
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public Long getRouteId() {
		return Long.parseLong(routeId);
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	@Override
	public String toString() {
		return "ScheduleParser [startDate=" + startDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", fareAmount=" + fareAmount + ", driverId=" + driverId + ", condId=" + condId + ", busId=" + busId
				+ ", routeId=" + routeId + "]";
	}
}
