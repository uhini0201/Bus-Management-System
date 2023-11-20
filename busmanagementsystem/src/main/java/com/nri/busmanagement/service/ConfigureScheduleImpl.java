package com.nri.busmanagement.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nri.busmanagement.model.Route;
import com.nri.busmanagement.model.Schedule;
import com.nri.busmanagement.repo.ScheduleRepository;

@Service
public class ConfigureScheduleImpl implements ConfigureSchedule{

	@Autowired
	private ScheduleRepository scheduleRepo;
	
	private List<Schedule> listSchedule;
	private List<Schedule> resultList;
	
	@Override
	public String addSchedule(Schedule schedule) {
		
		if(schedule.getBusobj()!=null && schedule.getCondobj()!=null && schedule.getDriverobj()!=null
				&& schedule.getRouteobj()!=null && schedule.getScheduleId()!=null && schedule.getFareAmount()!=null
				 && schedule.getStartTime()!=null && schedule.getEndTime()!=null) {
			
			
			this.scheduleRepo.save(schedule);
			return "Successfully added a schedule!";
		}
		
		return "Either one or multiple fields are missing! Please send a valid schedule to add to the database!";
	}

	@Override
	public String updateSchedule(Schedule updatedSchedule, Long scheduleId) {
		
		if (updatedSchedule!=null && scheduleId!=null) {
			try {
				Schedule existingSchedule = this.getSchedule(scheduleId).get();
				if(updatedSchedule.getBusobj()!=null && updatedSchedule.getCondobj()!=null 
						&& updatedSchedule.getDriverobj()!=null && updatedSchedule.getRouteobj()!=null 
						&& updatedSchedule.getScheduleId()!=null && updatedSchedule.getFareAmount()!=null
						&& updatedSchedule.getStartDate()!=null && updatedSchedule.getStartTime()!=null 
						&& updatedSchedule.getEndTime()!=null){
					
					existingSchedule.setScheduleId(updatedSchedule.getScheduleId());
					existingSchedule.setBusobj(updatedSchedule.getBusobj());
					existingSchedule.setCondobj(updatedSchedule.getCondobj());
					existingSchedule.setDriverobj(updatedSchedule.getDriverobj());
					existingSchedule.setRouteobj(updatedSchedule.getRouteobj());
					existingSchedule.setFareAmount(updatedSchedule.getFareAmount());
					existingSchedule.setStartDate(updatedSchedule.getStartDate());
					existingSchedule.setStartTime(updatedSchedule.getStartTime());
					existingSchedule.setEndTime(updatedSchedule.getEndTime());
					
					this.scheduleRepo.save(existingSchedule);
					
					return "Successfully updated the Route!";
				} else {
					return "Either one or multiple fields are missing!";
				}
			} catch (NoSuchElementException ex) {
				return "No schedule found with this Id";
			}
		}
		return "Either or both the updatedSchedule or scheduleId is empty/null";
	}

	@Override
	public Optional<Schedule> getSchedule(Long scheduleId) {
		return this.scheduleRepo.findById(scheduleId);
	}

	@Override
	public List<Schedule> getScheduleByOperatorName(String operatorName) {
		listSchedule=this.getAllSchedules();
		resultList=null;
		if(operatorName!=null) {
			resultList=listSchedule.stream().filter(schedule -> schedule.getCondobj().getCondName().equals(operatorName) 
					|| schedule.getDriverobj().getDriverName().equals(operatorName))
					.collect(Collectors.toList());
		}
		return resultList;
		
	}

	@Override
	public List<Schedule> getScheduleByArrivalAndDepartureTimes(Double startTime, Double endTime) {
		listSchedule=this.getAllSchedules();
		resultList=null;
		if(startTime!=null && endTime!=null)
			resultList=listSchedule.stream().filter(schedule -> schedule.getEndTime()<=endTime).collect(Collectors.toList());
		else if(endTime!=null && startTime!=null)
			resultList=listSchedule.stream().filter(schedule->schedule.getStartTime()>=startTime).collect(Collectors.toList());
		else if(startTime!=null && endTime!=null) 
			resultList=listSchedule.stream().filter(schedule -> schedule.getStartTime()>=startTime &&  schedule.getEndTime()<=endTime).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public List<Schedule> getScheduleByFareAmount(Double minFareAmount, Double maxFareAmount) {
		listSchedule=this.getAllSchedules();
		resultList=null;
		if(minFareAmount!=null && maxFareAmount!=null)
			resultList=listSchedule.stream().filter(schedule -> schedule.getFareAmount()<=maxFareAmount).collect(Collectors.toList());
		else if(maxFareAmount!=null && minFareAmount!=null)
			resultList=listSchedule.stream().filter(schedule->schedule.getFareAmount()>=minFareAmount).collect(Collectors.toList());
		else if(minFareAmount!=null && maxFareAmount!=null) 
			resultList=listSchedule.stream().filter(schedule -> schedule.getFareAmount()>=minFareAmount &&  schedule.getFareAmount()<=maxFareAmount).collect(Collectors.toList());
	
		return resultList;
	}

	@Override
	public List<Schedule> getScheduleByDate(Date startDate, Date endDate) {
		listSchedule=this.getAllSchedules();
		resultList=null;
		if(startDate!=null && endDate!=null)
			resultList=listSchedule.stream().filter(schedule -> schedule.getStartDate().before(endDate)).collect(Collectors.toList());
		else if(endDate!=null && startDate!=null)
			resultList=listSchedule.stream().filter(schedule->schedule.getStartDate().after(startDate)).collect(Collectors.toList());
		else if(startDate!=null && endDate!=null) 
			resultList=listSchedule.stream().filter(schedule -> schedule.getStartDate().after(startDate) &&  schedule.getStartDate().before(endDate)).collect(Collectors.toList());
	
		return resultList;
	}

	@Override
	public List<Schedule> getAllSchedules() {
		return this.scheduleRepo.findAll();
	}
	
	
	@Override
	public String deleteSchedule(Long scheduleId) {
		
		if (scheduleId!=null) {
			try {
				Schedule existingSchedule = this.getSchedule(scheduleId).get();
				this.scheduleRepo.deleteById(existingSchedule.getScheduleId());
				return "Successfully deleted the route!";
			}
			catch (NoSuchElementException ex) {
				return "Enter a valid routeId to delete the route";
			}
		}
		return "Please enter the routeId field";
	}

	@Override
	public List<Schedule> getSchedulefromRoute(Route route) {
		List<Schedule> allSchedules = scheduleRepo.findAll();
		return allSchedules.stream().filter(sObj -> sObj.getRouteobj().equals(route)).collect(Collectors.toList());
	}

}
