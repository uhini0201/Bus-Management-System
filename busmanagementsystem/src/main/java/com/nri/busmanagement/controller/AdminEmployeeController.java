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
import com.nri.busmanagement.model.Employee;
import com.nri.busmanagement.model.UserAuthentication;
import com.nri.busmanagement.model.UserRole;
import com.nri.busmanagement.repo.UserAuthenticationRepository;
import com.nri.busmanagement.service.EmployeeService;

@Controller
@RequestMapping("/admin")
public class AdminEmployeeController {

	@Autowired
	private EmployeeService empServ;
	
	private Employee prevEmployee;
	
	@Autowired
	private UserAuthenticationRepository userRepo;

	@GetMapping(path="/createEmployee")
	public String createEmployeeForm(Model model) {	
		
		Employee empModel=new Employee();
		model.addAttribute("empModel", empModel);
		
		return "admin_employee_add";
	}
	
	@PostMapping(path="/createEmployee")
	public String createEmployee(@ModelAttribute("empModel") Employee empModel, Model model) {
		
		empModel.setempRole(UserRole.EMPLOYEE);
		empModel.setWalletBalance(1000.0);
		
		
		userRepo.save(new UserAuthentication(empModel.getempEmail(),empModel.getempPassword(),"ROLE_"+empModel.getempRole()));
		
		
		this.empServ.addEmployee(empModel);
		
		return "redirect:/admin/viewAllEmployee";	// show a popup: "employee added"
	}
	
	@GetMapping("/viewAllEmployee")
	public String viewAllEmployee(Model model){
		
		List<Employee> lemp=empServ.getAllEmployees();	
		model.addAttribute("passengers", lemp);
        
		return "admin_employee_dash";
	}
	
	@GetMapping(path="/updateEmployee")
	public ModelAndView updateEmployee(@RequestParam String employeeId) {
		ModelAndView mav = new ModelAndView("admin_employee_update");
		Employee employee = empServ.getEmployeeById(employeeId).get();
		this.prevEmployee = employee;
		mav.addObject("empModel", employee);
		return mav;
	}
	
	@PostMapping(path="/updateEmployee")
	public String updateEmployee(@ModelAttribute Employee empModel) {
		System.out.println("Prev Employee " + prevEmployee);
		
		  UserRole role = null;
		  
		  if (prevEmployee.getempRole() == "ADMIN") role = UserRole.ADMIN; else if
		  (prevEmployee.getempRole().equalsIgnoreCase("EMPLOYEE")) role =
		  UserRole.EMPLOYEE; else if
		  (prevEmployee.getempRole().equalsIgnoreCase("DRIVER")) role =
		  UserRole.DRIVER; else if
		  (prevEmployee.getempRole().equalsIgnoreCase("CONDUCTOR")) role =
		  UserRole.CONDUCTOR; System.out.println(role);
		  
		  empModel.setempRole(role);
		 
		prevEmployee.setWalletBalance(empModel.getWalletBalance());
		
		empServ.updateEmployee(empModel);
		
		return "redirect:/admin/viewAllEmployee";
	}
	
	@GetMapping(path="/removeEmployee")
	public String removeEmployee(@RequestParam String employeeId) {
		empServ.deleteEmployeeById(employeeId);
		return "redirect:/admin/viewAllEmployee";
	}
}
