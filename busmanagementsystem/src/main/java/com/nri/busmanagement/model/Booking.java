package com.nri.busmanagement.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Booking{
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long bookingId;

	private LocalDate bookdate;
	private Double bookTime;//getTime();
	private String bookStatus;
	private Double fareAmount;
	
	
	private String empSrc;
	private String empDst;
//	private LocalDate srcTime;
	@OneToOne
	private Schedule scheduleobj;//foreign key

	@OneToOne(cascade = CascadeType.ALL)
	private Employee empobj;//foreign key
	
	public Booking() {
		super();
	}
	
	public Booking(Long bookingId, LocalDate bookdate, Double bookTime, String bookStatus, Double fareAmount, String empSrc,
			String empDst, Schedule scheduleobj, Employee empobj) {
		super();
		this.bookingId = bookingId;
		this.bookdate = bookdate;
		this.bookTime = bookTime;
		this.bookStatus = bookStatus;
		this.fareAmount = fareAmount;
		this.empSrc = empSrc;
		this.empDst = empDst;
		this.scheduleobj = scheduleobj;
		this.empobj = empobj;
	}

	public String getEmpSrc() {
		return empSrc;
	}

	public void setEmpSrc(String empSrc) {
		this.empSrc = empSrc;
	}

	public String getEmpDst() {
		return empDst;
	}

	public void setEmpDst(String empDst) {
		this.empDst = empDst;
	}

	public Employee getEmpobj() {
		return empobj;
	}

	public void setEmpobj(Employee empobj) {
		this.empobj = empobj;
	}

	public void setBookTime(Double bookTime) {
		this.bookTime = bookTime;
	}

	public void setFareAmount(Double fareAmount) {
		this.fareAmount = fareAmount;
	}

	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Schedule getScheduleobj() {
		return scheduleobj;
	}
	public void setScheduleobj(Schedule scheduleobj) {
		this.scheduleobj = scheduleobj;
	}
	public LocalDate getBookdate() {
		return bookdate;
	}
	public void setBookdate(LocalDate bookdate) {
		this.bookdate = bookdate;
	}
	public double getBookTime() {
		return bookTime;
	}
	public void setBookTime(double bookTime) {
		this.bookTime = bookTime;
	}
	public String getBookStatus() {
		return bookStatus.toString();
	}
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	public double getFareAmount() {
		return fareAmount;
	}
	public void setFareAmount(double fareAmount) {
		this.fareAmount = fareAmount;
	}
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", bookdate=" + bookdate + ", bookTime=" + bookTime + ", bookStatus="
				+ bookStatus + ", fareAmount=" + fareAmount + ", empSrc=" + empSrc + ", empDst=" + empDst
				+ ", scheduleobj=" + scheduleobj + ", empobj=" + empobj + "]";
	}
	
	
}
