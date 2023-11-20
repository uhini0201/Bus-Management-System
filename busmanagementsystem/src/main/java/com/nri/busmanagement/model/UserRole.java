package com.nri.busmanagement.model;

public enum UserRole {
	ADMIN,
	EMPLOYEE,
	DRIVER,
	CONDUCTOR;
	
	@Override
	public String toString() {
		switch (this) {
		case ADMIN:	return "ADMIN";
		case EMPLOYEE: return "EMPLOYEE";
		case DRIVER: return "DRIVER";
		case CONDUCTOR: return "CONDUCTOR";
		default: return "No Role Assigned";
		}
	}
}
