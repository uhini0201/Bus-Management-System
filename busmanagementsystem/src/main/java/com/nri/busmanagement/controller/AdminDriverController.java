package com.nri.busmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nri.busmanagement.model.Driver;
import com.nri.busmanagement.model.Employee;
import com.nri.busmanagement.model.UserAuthentication;
import com.nri.busmanagement.repo.UserAuthenticationRepository;
import com.nri.busmanagement.service.DriverService;

@Controller
@RequestMapping("/admin")
public class AdminDriverController {
	
	@Autowired
	private DriverService driverService;
	
	private Driver prevDriver;
	
	@Autowired
	private UserAuthenticationRepository userRepo;
	
	
	// To Send to driver add form to the admin
	@GetMapping("/createDriver")
	public String showAddForm(Model model) {
		Driver driver = new Driver();
		model.addAttribute(driver);
		return "admin_driver_add";
	}

	// To save the driver and redirect to the dashboard
	@PostMapping(path = "/createDriver")
	public String addDriver(@ModelAttribute("driver") Driver driver, Model model) {
		this.driverService.addDriver(driver);
		userRepo.save(new UserAuthentication(driver.getDriverEmail(),driver.getPassword(),"ROLE_"+driver.getEmpRole()));
		return "redirect:/admin/viewAllDriver";

	}

	// To view all the data in the dashboard
	@GetMapping(path = "/viewAllDriver")
	public String getAllDriver(Model model) {
		List<Driver> drivers = this.driverService.getAllDriver();
		model.addAttribute("users", drivers);
		return "admin_driver_dash";
	}

	// To send the update form to the user

	@GetMapping("/updateDriver")
	public ModelAndView showUpdateForm(@RequestParam String driverEmail) {
		ModelAndView mav = new ModelAndView("admin_driver_update");
		Driver driver = driverService.getDriverById(driverEmail).get();
		this.prevDriver = driver;
		mav.addObject("driver", driver);
		return mav;
	}

	// To save the updated driver data

	@PostMapping("/updateDriver")
	public String updateDriver(@ModelAttribute Driver driver) {
		this.driverService.updateDriver(driver);

		return "redirect:/admin/viewAllDriver";
	}

	// To delete a record from the dashboard

	@GetMapping("/removeDriver")
	public String deleteDriver(@RequestParam("driverEmail") String driverEmail) {
		this.driverService.deleteDriver(driverEmail);

		return "redirect:/admin/viewAllDriver";
	}

	// To search drivers by name
	@GetMapping("/viewDriverByName")
	public String getDriverByName(@RequestParam("query") String query, Model model) {
		List<Driver> drivers = this.driverService.getDriverByName(query);
		model.addAttribute("users", drivers);
		return "admin_driver_dash";
	}

}
