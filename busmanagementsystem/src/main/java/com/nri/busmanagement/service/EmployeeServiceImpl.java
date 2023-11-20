package com.nri.busmanagement.service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nri.busmanagement.model.Employee;
import com.nri.busmanagement.model.UserRole;
import com.nri.busmanagement.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(String id) {
		// Could implement a better way of handling if null object is returned
		return employeeRepo.findById(id);
	}

	@Override
	public boolean addEmployee(Employee emp) {
		Employee empobj = this.employeeRepo.save(emp);
		if (empobj != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String updateEmployee(Employee updatedEmployee) {
		System.out.println(updatedEmployee);
		if (updatedEmployee.getempEmail()==null && updatedEmployee==null) {
			// Employee Object and Employee ID cannot be NULL
			return "Employee Object and Employee ID cannot be NULL";
		}

		// Fetch Employee by ID
		// ! Issue: employee could be NULL, handling required
		Employee existingEmployee = this.employeeRepo.findById(updatedEmployee.getempEmail()).get();

		// Update Employee's name
		if (existingEmployee.getempEmail().equalsIgnoreCase(updatedEmployee.getempEmail())) {
			employeeRepo.save(updatedEmployee);
			
			return "Employee got updated.";
		}

		return "Something went wrong.";
	}

	@Override
	public void deleteEmployeeById(String empId) {
		employeeRepo.deleteById(empId);
	}

	@Override
	public List<Employee> getEmployeeByRoles(UserRole role) {
		return this.employeeRepo.findAll()
				.stream()
				.filter(lobj->lobj.getempRole()
						.equalsIgnoreCase(role.toString()))
				.collect(Collectors.toList());
	}

}
