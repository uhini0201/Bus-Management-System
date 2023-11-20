package com.nri.busmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nri.busmanagement.model.Bus;
import com.nri.busmanagement.model.Location;
import com.nri.busmanagement.model.Route;
import com.nri.busmanagement.model.Schedule;
import com.nri.busmanagement.service.BusService;
import com.nri.busmanagement.service.ConfigureRoute;
import com.nri.busmanagement.service.ConfigureSchedule;


@Controller
@RequestMapping("/bus")
public class BusController {

	@Autowired
	private BusService busService;
	
	@Autowired
	private ConfigureRoute routeServ;
	
	@Autowired
	private ConfigureSchedule scheduleServ;
	
	@GetMapping(path="/viewSchedules")
	public String viewAllSchedules(Model model) {
		List<List<Schedule>> lschedules = new ArrayList<>();
		model.addAttribute("schedules", lschedules);
		return "Bus.html";
	}
	
	@PostMapping(path="/viewSchedules")
	public String viewAllSchedules(@ModelAttribute Location location, Model model) {
		List<List<Schedule>> lschedules = new ArrayList<>();
		List<Route> lroutes = routeServ.getRoute(location.getAddressobj().getBusStopName());
		
		for (Route route : lroutes) {
			lschedules.add(scheduleServ.getSchedulefromRoute(route));
		}
		
		model.addAttribute("schedules", lschedules);
		
		return "Bus";
	}

	@GetMapping("/getBus/{id}")
	public Optional<Bus> getBus(@PathVariable long id) {
		return this.busService.getABusById(id);
	}

	@PutMapping("/updateBus")
	public String updateEmployee(@RequestBody Bus bus) {
		long bid = bus.getBusId();
		this.busService.updateBus(bus, bid);
		return "Bus record got updated";
	}

	@DeleteMapping("/deleteBus/{id}")
	public String deleteBus(@PathVariable long id) {
		this.busService.deleteBusById(id);
		return "Bus record got deleted";
	}

}
