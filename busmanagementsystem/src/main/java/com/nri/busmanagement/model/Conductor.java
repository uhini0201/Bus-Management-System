package com.nri.busmanagement.model;

import java.util.List;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Conductor{
	@Id
	private String condEmail;
	
	private String condName;
	private String condPhone;
	private String password;
	private UserRole empRole = UserRole.CONDUCTOR;
	private Double condSal;

	
	public Conductor() {
		super();
	}

	public Conductor(String condEmail, String condName, String condPhone, String password, UserRole empRole,
			Double condSal) {
		super();
		this.condEmail = condEmail;
		this.condName = condName;
		this.condPhone = condPhone;
		this.password = password;
		this.empRole = empRole;
		this.condSal = condSal;
	}

	public String getCondName() {
		return condName;
	}

	public void setCondName(String condName) {
		this.condName = condName;
	}

	public String getCondPhone() {
		return condPhone;
	}

	public void setCondPhone(String condPhone) {
		this.condPhone = condPhone;
	}

	public Double getCondSal() {
		return condSal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCondSal(Double condSal) {
		this.condSal = condSal;
	}

	public String getCondEmail() {
		return condEmail;
	}

	public void setCondEmail(String condEmail) {
		this.condEmail = condEmail;
	}

	public UserRole getEmpRole() {
		return empRole;
	}

	public void setEmpRole(UserRole empRole) {
		this.empRole = empRole;
	}

	@Override
	public String toString() {
		return "Conductor [condName=" + condName + ", condPhone=" + condPhone + ", condSal="
				+ condSal + ", condEmail=" + condEmail + "]";
	}

}
