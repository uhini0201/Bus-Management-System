package com.nri.busmanagement.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nri.busmanagement.model.Schedule;
import com.nri.busmanagement.service.ConfigureSchedule;

@RestController
@RequestMapping("/configSchedule")
public class ScheduleController {
	
	@Autowired
	private ConfigureSchedule configSchedule;
	
	@PostMapping(path="/createSchedule")
	public String addSchedule(@RequestBody Schedule schedule) {
		return this.configSchedule.addSchedule(schedule);
	}
	
	
	@PutMapping(path="/updateSchedule",consumes="application/json")
	public String updateSchedule(@RequestBody Schedule modifiedSchedule) {
		return this.configSchedule.updateSchedule(modifiedSchedule, modifiedSchedule.getScheduleId());
	}
	
	@GetMapping(path="/getScheduleById/{scheduleId}")
	public Optional<Schedule> getSchedule(@PathVariable("scheduleId") Long scheduleId){
		return this.configSchedule.getSchedule(scheduleId);
	}
	
	@GetMapping(path="/getScheduleByPersonName/{operatorName}")
	public List<Schedule> getScheduleByOperatorName(@PathVariable("operatorName") String operatorName){
		return this.configSchedule.getScheduleByOperatorName(operatorName);
	}
		
	@GetMapping(path="/getScheduleByTime/{startTime}/{endTime}")
	public List<Schedule> getScheduleByArrivalAndDepartureTimes(@PathVariable("startTime") Double startTime, @PathVariable("endTime") Double endTime){
		return this.configSchedule.getScheduleByArrivalAndDepartureTimes(startTime, endTime);
	}

	@GetMapping(path="/getScheduleByFare/{minFareAmount}/{maxFareAmount}")
	public List<Schedule> getScheduleByFareAmount(@PathVariable("minFareAmount") Double minFareAmount,@PathVariable("maxFareAmount") Double maxFareAmount){
		return this.configSchedule.getScheduleByFareAmount(minFareAmount, maxFareAmount);
	}
	
	@GetMapping(path="/getScheduleByDate/{startDate}/{endDate}")
	public List<Schedule> getScheduleByDate(@PathVariable("startDate") Date startDate,@PathVariable("endDate") Date endDate){
		return this.configSchedule.getScheduleByDate(startDate, endDate);
	}
	
	@GetMapping(path="/getAllSchedules")
	public List<Schedule> getAllSchedules(){
		return this.getAllSchedules();
	}
	
	@DeleteMapping(path="/deleteSchedule/{scheduleId}")
	public String deleteSchedule(@PathVariable("scheduleId") Long scheduleId) {
		return this.configSchedule.deleteSchedule(scheduleId);
	}
	
}
