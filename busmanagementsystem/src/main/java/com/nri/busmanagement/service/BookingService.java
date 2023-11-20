package com.nri.busmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nri.busmanagement.model.Booking;
import com.nri.busmanagement.model.Employee;


public interface BookingService {

	//Dao Services
	public List<Booking> getAllBookings();


	public Optional<Booking> getBookingById(Long bookingId);
	public boolean addBooking(Booking booking);
	public boolean updateBooking(Booking booking);
	public boolean deleteBooking(Long bookingId);
	
	//Interface Services
	public boolean getSeatAvailability(Long busId, int seatsRequired);
	public Booking bookTicket(Booking booking);
	public boolean cancelTicket(Long bookingId);
	public boolean updateSeat(Long bookingId, int seat);
	
}
