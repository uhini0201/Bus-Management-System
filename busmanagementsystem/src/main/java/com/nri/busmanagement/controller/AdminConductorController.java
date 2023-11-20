package com.nri.busmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nri.busmanagement.model.Conductor;
import com.nri.busmanagement.model.Employee;
import com.nri.busmanagement.model.UserAuthentication;
import com.nri.busmanagement.repo.UserAuthenticationRepository;
import com.nri.busmanagement.service.ConductorService;

@Controller
@RequestMapping("/admin")
public class AdminConductorController {

	@Autowired
	private ConductorService conductorService;
	
	private Conductor prevCond;
	
	@Autowired
	private UserAuthenticationRepository userRepo;
	
	@GetMapping("/createConductor")
	public String showAddForm(Model model) {
		Conductor conductor = new Conductor();
		model.addAttribute(conductor);
		return "admin_conductor_add";
	} // To recieve submitted data form the admin_conductor_add.html and redirect to
		// the viewConductor endpoint

	@PostMapping("/createConductor")
	public String addConductor(Conductor conductor, Model model) {
		this.conductorService.addConductor(conductor);
		userRepo.save(new UserAuthentication(conductor.getCondEmail(),conductor.getPassword(),"ROLE_"+conductor.getEmpRole()));
		
		return "redirect:/admin/viewAllConductor";

	} //// To Send to conductor update form to the admin

	@GetMapping("/updateConductor")
	public ModelAndView showUpdateForm(@RequestParam String condEmail) {
		ModelAndView mav = new ModelAndView("admin_conductor_update");
		Conductor conductor = conductorService.getConductorById(condEmail).get();
		this.prevCond = conductor;
		mav.addObject("conductor", conductor);
		return mav;
	} // To recieve submitted data form the admin_conductor_update.html and redirect
		// to the viewConductor endpoint

	@PostMapping("/updateConductor")
	public String updateConductor(Conductor conductor, Model model) {
		this.conductorService.updateConductor(conductor);
		return "redirect:/admin/viewAllConductor";
	} // To show all stored conductor record

	@GetMapping("/viewAllConductor")
	public String getAllConductor(Model model) {
		List<Conductor> conductors = this.conductorService.getAllConductor();
		model.addAttribute("users", conductors);
		return "admin_conductor_dash";
	}

	@GetMapping("/viewConductorById")
	public String getConductorById(@RequestParam("query") String query, Model model) {
		// System.out.println(query);
		Optional<Conductor> res = this.conductorService.getConductorById(query);
		model.addAttribute("conductor", res);
		return "success";
	}

	@GetMapping("/viewConductorByName")
	public String getConductor(@RequestParam("query") String query, Model model) {
		List<Conductor> users = this.conductorService.getConductorByName(query);
		model.addAttribute("users", users);
		return "admin_conductor_dash";
	}

	@GetMapping("/removeConductor")
	public String deleteConductor(@RequestParam("condEmail") String condEmail) {
		System.out.println("delete");
		this.conductorService.deleteConductor(condEmail);
		return "redirect:/admin/viewAllConductor";
	}

}
