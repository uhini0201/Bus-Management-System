package com.nri.busmanagement.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nri.busmanagement.model.Bus;
import com.nri.busmanagement.model.Conductor;
import com.nri.busmanagement.model.CustomUserDetails;
import com.nri.busmanagement.model.Schedule;
import com.nri.busmanagement.service.BusService;
import com.nri.busmanagement.service.ConductorService;
import com.nri.busmanagement.service.ConfigureSchedule;
import com.nri.busmanagement.service.WaitingService;

@Controller
@RequestMapping("/conductor")
public class ConductorController {

	@Autowired
	private ConductorService conductorService;
	
	@Autowired
	private WaitingService waitingService;
	
	@Autowired
	private ConfigureSchedule scheduleService;
	
	@Autowired
	private BusService busService;
	
	
	List<Schedule> activeSchedules;
	
	
	@GetMapping("/viewAllConductors")
	public List<Conductor> getAllConductor(){
		return this.conductorService.getAllConductor();
	}
	
	@GetMapping("/viewConductorById/{id}")
	public Optional<Conductor> getConductorById(@PathVariable("id") String conductorId) {
		return this.conductorService.getConductorById(conductorId);
	}
	
	@GetMapping("/viewConductorByName/{name}")
	public List<Conductor> getConductor(@PathVariable("name") String conductorName) {
		return this.conductorService.getConductorByName(conductorName);
	}
	
	@PostMapping("/addConductor")
	public String addConductor(@RequestBody Conductor conductor) {
		return this.conductorService.addConductor(conductor);
		
	}
	
	@PutMapping("/updateConductor")
	public String updateConductor(@RequestBody Conductor conductor) {
		return this.conductorService.updateConductor(conductor);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteConductor(@PathVariable("id") String conductorId) {
		return this.conductorService.deleteConductor(conductorId);
	}
	
	
	@GetMapping("/showTrips")
	public String showTrips(Model model) {
		String conductorEmail = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		List<Schedule> scheduleList = this.scheduleService.getAllSchedules();
        activeSchedules = scheduleList.stream().filter(sobj -> sobj.getCondobj().getCondEmail().equals(conductorEmail)).collect(Collectors.toList());
		
        System.out.println("GET /showTrips " + activeSchedules);

		model.addAttribute("trips", activeSchedules);
		return "admin_conductor_interface";
		
	}
	
	@GetMapping("/removeWaitingList")
    public String clearWaitlingList(Model model) {
        
        return "redirect:/conductor/showTrips";
    }
	
	@PostMapping("/removeWaitingList")
    public String clearWaitlingList() {
        for (Schedule schedule : activeSchedules) {
        	Bus bus = schedule.getBusobj();
        	bus.setBookedSeats(0);
        	bus.setRemainingSeats(bus.getBusCapacity());
        	busService.updateBus(bus, bus.getBusId());
        	
            this.waitingService.removeWaiting(schedule.getBusobj());
        }
        
        return "redirect:/conductor/showTrips";
    }
}
