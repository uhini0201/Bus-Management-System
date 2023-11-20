package com.nri.busmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nri.busmanagement.model.Employee;
import com.nri.busmanagement.model.UserRole;



public interface EmployeeService {

	public List<Employee> getAllEmployees();
	
	public Optional<Employee> getEmployeeById(String id);
	
	public boolean addEmployee(Employee emp);
	
	public String updateEmployee(Employee emp);
	
	public void deleteEmployeeById(String empId);
	
	public List<Employee> getEmployeeByRoles(UserRole role);
}
