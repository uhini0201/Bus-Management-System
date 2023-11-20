package com.nri.busmanagement.model;

import java.util.List;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Driver{
	@Id
	private String driverEmail;
	
	private String driverName;
	private String driverPhone;
	private String password;
	private UserRole empRole;
	private Double driverSal;
	
	public Driver() {
		super();
	}
	

	public Driver(String driverEmail, String driverName, String driverPhone, String password, UserRole empRole,
			Double driverSal) {
		super();
		this.driverEmail = driverEmail;
		this.driverName = driverName;
		this.driverPhone = driverPhone;
		this.password = password;
		this.empRole = empRole;
		this.driverSal = driverSal;
	}


	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Double getDriverSal() {
		return driverSal;
	}
	public void setDriverSal(Double driverSal) {
		this.driverSal = driverSal;
	}

	public String getDriverEmail() {
		return driverEmail;
	}
	public void setDriverEmail(String driverEmail) {
		this.driverEmail = driverEmail;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverPhone() {
		return driverPhone;
	}
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}
	

	public UserRole getEmpRole() {
		return empRole;
	}

	public void setEmpRole(UserRole empRole) {
		this.empRole = empRole.DRIVER;
	}


	@Override
	public String toString() {
		return "Driver [driverEmail=" + driverEmail + ", driverName=" + driverName + ", driverPhone=" + driverPhone
				+ ", password=" + password + ", empRole=" + empRole + ", driverSal=" + driverSal + "]";
	}

	
}
