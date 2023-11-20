package com.nri.busmanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long payId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonProperty(access = Access.READ_WRITE)
	private Booking bookobj;

	public Payment() {
		super();
	}

	public Payment(Long payId, Booking bookobj) {
		super();
		this.payId = payId;
		this.bookobj = bookobj;
	}

	public Long getPayId() {
		return payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}

	public Booking getBookobj() {
		return bookobj;
	}

	public void setBookibj(Booking bookobj) {
		this.bookobj = bookobj;
	}

	@Override
	public String toString() {
		return "Payment [payId=" + payId + ", bookobj=" + bookobj + "]";
	}
}
