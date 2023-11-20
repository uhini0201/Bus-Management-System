package com.nri.busmanagement.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	private String empEmail;
	
	private String empName;
	private String empPassword;
	private String empPhone;
	
	private String empRole;
	private String empAccountStatus;
	private double walletBalance;
	
	public Employee() {
		super();
	}
	public Employee(String empName, String empEmail, String empPassword, String empPhone, UserRole empRole,
			String empAccountStatus, double walletBalance) {
		super();
		this.empName = empName;
		this.empEmail = empEmail;
		this.empPassword = empPassword;
		this.empPhone = empPhone;
		this.empRole = UserRole.EMPLOYEE.toString();
		this.empAccountStatus = empAccountStatus;
		this.walletBalance = walletBalance;
	}
	public String getempName() {
		return empName;
	}
	public void setempName(String empName) {
		this.empName = empName;
	}
	public String getempEmail() {
		return empEmail;
	}
	public void setempEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getempPassword() {
		return empPassword;
	}
	public void setempPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public String getempPhone() {
		return empPhone;
	}
	public void setempPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	public String getempRole() {
		return empRole.toString();
	}
	public void setempRole(UserRole empRole) {
		this.empRole = empRole.toString();
	}
	public String getempAccountStatus() {
		return empAccountStatus;
	}
	public void setempAccountStatus(String empAccountStatus) {
		this.empAccountStatus = empAccountStatus;
	}
	
	public double getWalletBalance() {
		return walletBalance;
	}
	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}
	
	@Override
	public String toString() {
		return "employee [empName=" + empName + ", empEmail=" + empEmail + ", empPassword="
				+ empPassword + ", empPhone=" + empPhone + ", empRole=" + empRole + ", empAccountStatus="
				+ empAccountStatus + ", walletBalance=" + walletBalance + "]";
	}
	
	
}
