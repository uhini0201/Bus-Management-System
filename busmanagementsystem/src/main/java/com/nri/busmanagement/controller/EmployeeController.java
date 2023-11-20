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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nri.busmanagement.model.Booking;
import com.nri.busmanagement.model.CustomUserDetails;
import com.nri.busmanagement.model.Employee;
import com.nri.busmanagement.model.UserAuthentication;
import com.nri.busmanagement.model.UserRole;
import com.nri.busmanagement.service.BookingService;
import com.nri.busmanagement.service.EmployeeService;
import com.nri.busmanagement.service.EmployeeServiceImpl;

@Controller
@RequestMapping("/user")
public class EmployeeController {

	@Autowired
	private EmployeeService empServ;
	
	@Autowired
	private BookingService bookingServ;
	
	Employee currEmployee;
	UserAuthentication currUser;
	
	@GetMapping("/profile")
	public String getProfile(Model model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		currEmployee = this.empServ.getEmployeeById(user.getUsername()).get();
		
		List<Booking> bookingHist = this.bookingServ.getAllBookings().stream().filter(bobj -> bobj.getEmpobj().equals(currEmployee)).collect(Collectors.toList());
		
		model.addAttribute("employee", currEmployee);
		model.addAttribute("history", bookingHist);
		return "profile";
	}
	
	@GetMapping("/changePassword")
	public String changePassword() {
		System.out.println("get pass");
		return "change_password";
	}
	
	@PostMapping("/changePassword")
	public String changePassword(@PathVariable String newPassword) {
		System.out.println(newPassword);
		currEmployee.setempPassword(newPassword);
		currUser.setPassword(newPassword);
		empServ.updateEmployee(currEmployee);
		
		return "redirect:/user/profile";
	}
	
	
	
	
	
}
