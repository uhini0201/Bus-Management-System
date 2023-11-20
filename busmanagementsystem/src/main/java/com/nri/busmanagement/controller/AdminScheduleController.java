package com.nri.busmanagement.controller;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nri.busmanagement.model.Employee;
import com.nri.busmanagement.model.Schedule;
import com.nri.busmanagement.model.ScheduleParser;
import com.nri.busmanagement.model.UserRole;
import com.nri.busmanagement.service.BusService;
import com.nri.busmanagement.service.ConductorService;
import com.nri.busmanagement.service.ConfigureRoute;
import com.nri.busmanagement.service.ConfigureSchedule;
import com.nri.busmanagement.service.DriverService;

@Controller
@RequestMapping("/admin")
public class AdminScheduleController {

	@Autowired
	private ConfigureSchedule scheduleServ;

	@Autowired
	private BusService busServ;

	@Autowired
	private DriverService driverServ;

	@Autowired
	private ConductorService condServ;

	@Autowired
	private ConfigureRoute routeServ;

	@GetMapping(path = "/createSchedule")
	public String createEmployeeForm(Model model) {

		Schedule scheduleModel = new Schedule();
		model.addAttribute("scheduleModel", scheduleModel);
		model.addAttribute("buses", busServ.getAllBuses());
		model.addAttribute("drivers", driverServ.getAllDriver());
		model.addAttribute("conductors", condServ.getAllConductor());
		model.addAttribute("routes", routeServ.getAllRoutes());

		return "admin_schedule_add";
	}

	@PostMapping(path = "/createSchedule")
	public String createEmployee(@RequestBody ScheduleParser schd, Model model) throws ParseException {

		Schedule schedule = new Schedule();

		schedule.setScheduleId((long) 0);
		schedule.setStartDate(new SimpleDateFormat("yyyy-mm-dd").parse(schd.getStartDate()));
		schedule.setStartTime(Double.parseDouble(schd.getStartTime()));
		schedule.setEndTime(Double.parseDouble(schd.getEndTime()));
		schedule.setFareAmount(Double.parseDouble(schd.getFareAmount()));
		schedule.setDriverobj(driverServ.getAllDriver().stream()
				.filter(dobj -> dobj.getDriverEmail().equalsIgnoreCase(schd.getDriverId())).findFirst().get());
		schedule.getDriverobj().setEmpRole(UserRole.DRIVER);
		schedule.setCondobj(condServ.getAllConductor().stream()
				.filter(cobj -> cobj.getCondEmail().equalsIgnoreCase(schd.getCondId())).findFirst().get());
		schedule.setBusobj(busServ.getAllBuses().stream().filter(bobj -> bobj.getBusId().equals(schd.getBusId()))
				.findFirst().get());
		schedule.setRouteobj(routeServ.getAllRoutes().stream()
				.filter(robj -> robj.getRouteId().equals(schd.getRouteId())).findFirst().get());

		this.scheduleServ.addSchedule(schedule);
		System.out.println("POST /createSchedule Schedules: " + schedule);

		return "redirect:/admin/viewAllSchedule"; // show a popup: "employee added"
	}

	/*
	 * @PostMapping(path="/createSchedule") public String
	 * createEmployee(@ModelAttribute ScheduleParser schd, Model model) throws
	 * ParseException {
	 * 
	 * Schedule schedule = new Schedule();
	 * 
	 * schedule.setScheduleId((long) 0); schedule.setStartDate(new
	 * SimpleDateFormat("yyyy-mm-dd").parse(schd.getStartDate()));
	 * schedule.setStartTime(Double.parseDouble(schd.getStartTime()));
	 * schedule.setEndTime(Double.parseDouble(schd.getEndTime()));
	 * schedule.setFareAmount(Double.parseDouble(schd.getFareAmount()));
	 * schedule.setDriverobj(driverServ.getAllDriver().stream().filter(dobj ->
	 * dobj.getDriverEmail().equalsIgnoreCase(schd.getDriverId())).findFirst().get()
	 * ); schedule.getDriverobj().setEmpRole(UserRole.DRIVER);
	 * schedule.setCondobj(condServ.getAllConductor().stream().filter(cobj ->
	 * cobj.getCondEmail().equalsIgnoreCase(schd.getCondId())).findFirst().get());
	 * schedule.setBusobj(busServ.getAllBuses().stream().filter(bobj ->
	 * bobj.getBusId().equals(schd.getBusId())).findFirst().get());
	 * schedule.setRouteobj(routeServ.getAllRoutes().stream().filter(robj ->
	 * robj.getRouteId().equals(schd.getRouteId())).findFirst().get());
	 * 
	 * this.scheduleServ.addSchedule(schedule);
	 * System.out.println("POST /createSchedule Schedules: " + schedule);
	 * 
	 * return "redirect:/admin/viewAllSchedule"; // show a popup: "employee added" }
	 */

	@GetMapping(path = "/viewAllSchedule")
	public String getAllSchedules(Model model) {
		List<Schedule> lschdule = this.scheduleServ.getAllSchedules();

		model.addAttribute("lschdule", lschdule);
		System.out.println(lschdule);
		return "admin_schedule_dash";
	}

	@GetMapping(path = "/removeSchedule")
	public String removeSchedule(@RequestParam("scheduleId") Long scheduleId) {
		this.scheduleServ.deleteSchedule(scheduleId);
		return "redirect:/admin/viewAllSchedule";
	}
}
