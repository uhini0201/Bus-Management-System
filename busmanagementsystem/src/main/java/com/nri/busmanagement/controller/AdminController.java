package com.nri.busmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	@GetMapping("/panel")
	public String adminPanel() {
		return "admin_index.html";
	}
	
	@GetMapping("/trips")
	public String conInterface() {
		return "admin_conductor_interface.html";
	}
}