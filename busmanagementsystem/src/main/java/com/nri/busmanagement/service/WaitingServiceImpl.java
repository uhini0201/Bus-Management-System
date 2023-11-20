package com.nri.busmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nri.busmanagement.model.Booking;
import com.nri.busmanagement.model.Bus;
import com.nri.busmanagement.model.Employee;
import com.nri.busmanagement.model.Status;
import com.nri.busmanagement.model.WaitingList;
import com.nri.busmanagement.repo.BookingRepository;
import com.nri.busmanagement.repo.WaitingRepo;

@Service
public class WaitingServiceImpl implements WaitingService {

	@Autowired
	private WaitingRepo waitingRepo;

//	@Autowired
//	private BookingServiceImpl bookingService;

	@Autowired
	private BookingRepository bookingRepo;

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@Override
	public List<WaitingList> viewAllWaiting() {

		return this.waitingRepo.findAll();
	}

	@Override
	public String addWaiting(WaitingList waiting) {

		if (waiting!=null) {
			this.waitingRepo.save(waiting);
			return "Added successfully in the waiting list";
		} else {
			return "Something went wrong";
		}
	}

	// when the bus completes it's trip (conductor fires this event) , all the
	// employees in the waitlist for that bus should be refunded
	@Override
	public String removeWaiting(Bus bus) {

		if (bus!=null) {
			WaitingList waitingList = this.viewByBus(bus);

			List<Booking> pendingList = waitingList.getWailtList();
			
			//refunding
			for (Booking book : pendingList) {
				Employee employee = book.getEmpobj();
				employee.setWalletBalance(employee.getWalletBalance() + book.getFareAmount());
				this.employeeServiceImpl.updateEmployee(employee);

			}

			//removing that row from waitListing
			if (waitingList!=null) {
				this.waitingRepo.delete(waitingList);
				return "Remove successfully from waiting list";
			} else {
				return "Does not exist in the waiting list";
			}
		} else {
			return "This id is not vaild";
		}

		// return "function under construction";

	}

	@Override
	public WaitingList viewByBus(Bus bus) {

		List<WaitingList> allWaitingList = this.viewAllWaiting();
		for (WaitingList waitingList : allWaitingList) {
			if (waitingList.getBusobj().getBusId().equals(bus.getBusId())) {
				return waitingList;
			}
		}

		return null;

	}

	@Override
	public String updateWaiting(WaitingList waiting) {
		if (waiting!=null) {
			this.waitingRepo.save(waiting);
			return "Added successfully in the waiting list";
		} else {
			return "Something went wrong";
		}
	}

	@Override
	public void getFirstBooking(Bus bus) {
		WaitingList waitinglist = this.viewByBus(bus);
		
		if(waitinglist==null || waitinglist.getWailtList().isEmpty()) {
			return;
		}
		
		Booking tempBooking = waitinglist.getWailtList().get(0);
		waitinglist.getWailtList().remove(0);
		tempBooking.setBookStatus(Status.BOOKED.toString());
		bookingRepo.save(tempBooking);
		
	}

}
