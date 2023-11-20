package com.nri.busmanagement.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long scheduleId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	private Double startTime;
	private Double endTime;
	
	private Double fareAmount;
	
	@OneToOne
	private Driver driverobj;
	
	@OneToOne
	private Conductor condobj;
	
	@OneToOne
	private Bus busobj;
	
	@OneToOne
	private Route routeobj;

	public Schedule() {
		super();
	}

	public Schedule(Long scheduleId, Driver driverobj, Conductor condobj, Bus busobj, Date startDate, Double startTime,
			Double endTime, Double fareAmount, Route routeobj) {
		super();
		this.scheduleId = scheduleId;
		this.driverobj = driverobj;
		this.condobj = condobj;
		this.busobj = busobj;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.fareAmount = fareAmount;
		this.routeobj = routeobj;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Driver getDriverobj() {
		return driverobj;
	}

	public void setDriverobj(Driver driverobj) {
		this.driverobj = driverobj;
	}

	public Conductor getCondobj() {
		return condobj;
	}

	public void setCondobj(Conductor condobj) {
		this.condobj = condobj;
	}

	public Bus getBusobj() {
		return busobj;
	}

	public void setBusobj(Bus busobj) {
		this.busobj = busobj;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Double getStartTime() {
		return startTime;
	}

	public void setStartTime(Double startTime) {
		this.startTime = startTime;
	}

	public Double getEndTime() {
		return endTime;
	}

	public void setEndTime(Double endTime) {
		this.endTime = endTime;
	}

	public Double getFareAmount() {
		return fareAmount;
	}

	public void setFareAmount(Double fareAmount) {
		this.fareAmount = fareAmount;
	}

	public Route getRouteobj() {
		return routeobj;
	}

	public void setRouteobj(Route routeobj) {
		this.routeobj = routeobj;
	}

	@Override
	public String toString() {
		return "Schedule [scheduleId=" + scheduleId + ", driverobj=" + driverobj + ", condobj=" + condobj + ", busobj="
				+ busobj + ", startDate=" + startDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", fareAmount=" + fareAmount + ", routeobj=" + routeobj + "]";
	}
	
	
	
}
