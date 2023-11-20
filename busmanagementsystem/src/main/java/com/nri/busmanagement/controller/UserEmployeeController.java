package com.nri.busmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nri.busmanagement.model.Employee;
import com.nri.busmanagement.model.UserRole;
import com.nri.busmanagement.service.EmployeeServiceImpl;

@Controller
@RequestMapping("/user")
public class UserEmployeeController {

	@Autowired
	private EmployeeServiceImpl empServ;
	
	private Employee prevEmployee;
	
	@GetMapping(path="/updateEmployee")
	public ModelAndView update(@RequestParam String employeeId) {
		ModelAndView mav = new ModelAndView("admin_employee_update");
		Employee employee = empServ.getEmployeeById(employeeId).get();
		this.prevEmployee = employee;
		mav.addObject("empModel", employee);
		return mav;
	}
	
	@PostMapping(path="/updateEmployee")
	public String update(@ModelAttribute Employee empModel) {
		UserRole role = null;
		
		if (prevEmployee.getempRole() == "ADMIN")
			role = UserRole.ADMIN;
		else if (prevEmployee.getempRole().equalsIgnoreCase("EMPLOYEE"))
			role = UserRole.EMPLOYEE;
		else if (prevEmployee.getempRole().equalsIgnoreCase("DRIVER"))
			role = UserRole.DRIVER;
		else if (prevEmployee.getempRole().equalsIgnoreCase("CONDUCTOR"))
			role = UserRole.CONDUCTOR;
		
		empModel.setempRole(role);
		empModel.setWalletBalance(prevEmployee.getWalletBalance());
		
		empServ.updateEmployee(empModel);
		
		return "redirect:/viewAllEmployee";
	}
	
}
