package com.nri.busmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nri.busmanagement.model.Bus;
import com.nri.busmanagement.model.Employee;
import com.nri.busmanagement.model.UserRole;
import com.nri.busmanagement.service.BusService;

@Controller
@RequestMapping("/admin")
public class AdminBusController {

	@Autowired
	private BusService busService;

	private Bus prevBus;

	/** Bus Handlers **/

	@GetMapping(path = "/createBus")
	public String createBusForm(Model model) {
		Bus busModel = new Bus();
		model.addAttribute("busModel", busModel);
		// System.out.println(busModel);
		return "admin_bus_add";
	}

	@PostMapping(path = "/createBus")
	public String addBus(@ModelAttribute("busModel") Bus busModel) {
		// busModel.setBusId((long)1);
		busModel.setBookedSeats(0);
		busModel.setRemainingSeats(busModel.getBusCapacity());
		// System.out.println(busModel);
		this.busService.addBus(busModel);
		//System.out.println(busModel);
		return "redirect:/admin/viewAllBus";
	}

	@GetMapping("/viewAllBus")
	public String getAllBus(Model model) {
		List<Bus> lbus = this.busService.getAllBuses();
		model.addAttribute("buses", lbus);
		return "admin_bus_dash";

	}

	@GetMapping("/updateBus")
	public ModelAndView update(@RequestParam("busId") String busId) {
		ModelAndView mav = new ModelAndView("admin_bus_update");
		//System.out.println(mav);
		Bus bus = busService.getABusById(Long.parseLong(busId)).get();
		//System.out.println(bus);
		this.prevBus = bus;
		mav.addObject("busModel", bus);
		return mav;
	}

	@PostMapping(path = "/updateBus")
	public String update(@ModelAttribute Bus busModel) {
		busService.updateBus(busModel, busModel.getBusId());
		return "redirect:/admin/viewAllBus";
	}
	
	@GetMapping(path="/removeBus")
	public String delete(@RequestParam String busId) {
		busService.deleteBusById(Long.parseLong(busId));
		return "redirect:/admin/viewAllBus";
	}

}
