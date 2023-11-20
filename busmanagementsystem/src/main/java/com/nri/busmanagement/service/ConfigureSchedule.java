package com.nri.busmanagement.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nri.busmanagement.model.Route;
import com.nri.busmanagement.model.Schedule;

public interface ConfigureSchedule {
	
	public String addSchedule(Schedule schedule);
	public String updateSchedule(Schedule updatedSchedule, Long scheduleId);
	public Optional<Schedule> getSchedule(Long scheduleId);
	public List<Schedule> getScheduleByOperatorName(String operatorName);
	public List<Schedule> getScheduleByArrivalAndDepartureTimes(Double startTime, Double endTime);
	public List<Schedule> getScheduleByFareAmount(Double minFareAmount,Double maxFareAmount);
	public List<Schedule> getScheduleByDate(Date startDate,Date endDate);
	public List<Schedule> getAllSchedules();
	public String deleteSchedule(Long scheduleId);
	
	public List<Schedule> getSchedulefromRoute(Route route);

}
