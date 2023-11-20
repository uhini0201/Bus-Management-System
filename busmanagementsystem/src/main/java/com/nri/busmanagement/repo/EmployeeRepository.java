package com.nri.busmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nri.busmanagement.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
