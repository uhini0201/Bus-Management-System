package com.nri.busmanagement.exceptions;

public class WalletExceptionResponse {
	
	private String id;

	public WalletExceptionResponse() {
		super();
	}

	public WalletExceptionResponse(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "WalletExceptionResponse [id=" + id + "]";
	}

	

}
