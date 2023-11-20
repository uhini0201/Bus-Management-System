package com.nri.busmanagement.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.nri.busmanagement.model.Booking;
import com.nri.busmanagement.model.Bus;
import com.nri.busmanagement.model.Employee;
import com.nri.busmanagement.model.Status;
import com.nri.busmanagement.model.WaitingList;
import com.nri.busmanagement.repo.BookingRepository;
import com.nri.busmanagement.repo.BusRepository;
import com.nri.busmanagement.repo.EmployeeRepository;
import com.nri.busmanagement.repo.WaitingRepo;

import javax.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private BusRepository busRepo;
	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private WaitingServiceImpl waitingService;

	@Autowired
	private WaitingRepo waitRepo;

	@Override
	public List<Booking> getAllBookings() {

		return bookingRepo.findAll();
	}

	@Override
	public Optional<Booking> getBookingById(Long bookingId) {

		return Optional.of(bookingRepo.findById(bookingId).get());
	}

	@Override
	@Transactional
	public boolean addBooking(Booking booking) {

		Booking bookingobj = bookingRepo.save(booking);
		if (bookingobj != null) {
			return true;
		} else {
			return false;
		}

	}

	// waitlisting handling(conductor)
	@Override
	public boolean updateBooking(Booking updatedBooking) {
		if (updatedBooking!=null && updatedBooking.getBookingId() != null) {
			// Booking Object and Booking ID cannot be NULL
			return false;
		}

		// Fetch Booking by ID
		// ! Issue: booking could be NULL, handling required
		Optional<Booking> existingBooking = this.getBookingById((long) updatedBooking.getBookingId());

		// Update Booking Status
		if (existingBooking != null) {
//			existingBooking.get().setBookStatus(updatedBooking.getBookStatus());
//			existingBooking.get().setScheduleobj(updatedBooking.getScheduleobj());

			bookingRepo.save(updatedBooking);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean deleteBooking(Long bookingId) {
		try {
			bookingRepo.deleteById(bookingId);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean getSeatAvailability(Long busId, int seatsRequired) {

		Optional<Bus> busObj = busRepo.findById(busId);

		if (busObj != null) {
			if (busObj.get().getRemainingSeats() < seatsRequired) {
				return false;
			} else {
				return true;
			}
		}

		// if null
		return false;
	}

	@Override
	public Booking bookTicket(Booking booking) {
		Booking bookingDB = null;

		List<Employee> emps = empRepo.findAll();
		Employee empBook = booking.getEmpobj();
		/* System.out.println("booking"); */
		// bookingRepo.addBooking(booking);
		// getting the seat and updating it as booked by fetching the schedule and the
		// bus and its seats info
		
		System.out.println("Before Seat Avl check");
		if(!(booking.getBookStatus().equalsIgnoreCase("CANCELLED"))) {
			if (getSeatAvailability(booking.getScheduleobj().getBusobj().getBusId(), 1)) {
				System.out.println("After Seat Avl check");
				for (Employee eIterator : emps) {
					if (eIterator.getempEmail() == empBook.getempEmail()
							&& empBook.getempAccountStatus().equalsIgnoreCase("ACTIVE")) {

						System.out.println("After Email and Status Check");
						
						if (booking != null) {
							int books = booking.getScheduleobj().getBusobj().getBookedSeats();

							int bookr = booking.getScheduleobj().getBusobj().getRemainingSeats();

							Bus busobj = busRepo.getById(booking.getScheduleobj().getBusobj().getBusId());
							books = books + 1;
							busobj.setBookedSeats(books);
							bookr = bookr - 1;
							busobj.setRemainingSeats(bookr);
							System.out.println(busobj);
							busRepo.save(busobj);
							double fare = booking.getEmpobj().getWalletBalance()
									- (booking.getScheduleobj().getFareAmount());
							booking.getEmpobj().setWalletBalance(fare);
							
							System.out.println("BookingService | Booking Object(booking): " + booking);
							
							 bookingDB = bookingRepo.save(booking); 
							
							System.out.println("BookingService | BookingDB: " + bookingDB);
						}
						bookingDB = bookingRepo.save(booking);
						return bookingDB;
					} else {
						// if the employee never availed the service
					}

				}
			} else {
				booking.getEmpobj().setWalletBalance(
						booking.getEmpobj().getWalletBalance() - booking.getScheduleobj().getFareAmount());
				booking.setBookStatus(Status.WAITLIST.toString());

				Booking bookingNew = bookingRepo.save(booking);

				Bus bus = booking.getScheduleobj().getBusobj();

				WaitingList waitingList = this.waitingService.viewByBus(bus);

				/* System.out.println(waitingList); */
				if (waitingList!=null) {
					List<Booking> bookingList = waitingList.getWailtList();

					bookingList.add(bookingNew);

					waitingList.setWailtList(bookingList);

					this.waitingService.updateWaiting(waitingList);
				} else {
					/* System.out.println("In else of waitlisting"); */
					WaitingList waiting = new WaitingList();

					waiting.setBusobj(bus);

					List<Booking> bookingList = new ArrayList();

					bookingList.add(bookingNew);

					System.out.println(bookingList);

					waiting.setWailtList(bookingList);

					waiting.setId((long) 2);

					/* System.out.println(waiting); */
					System.out.println(this.waitRepo.save(waiting));

				}
				return bookingDB;

			}
		}
		else {
			bookingDB=bookingRepo.save(booking);
		}

		return bookingDB;
	}

	@Override
	public boolean cancelTicket(Long bookingId) {
		boolean res = false;

		// fetching the booking obj that needs to be cancelled
		Optional<Booking> bookingobj = (bookingRepo).findById(bookingId);

		// fetch current time and add 2 hours to it
		LocalTime time = LocalTime.now();
		time.plusHours(2);

		// if the added time is still before the starting time of the bus schedule then
		// only proceed
		// if(bookingobj.get().getScheduleobj().getStartTime().isAfter(time)) {
		// fetching the bus of that particular booking
		Bus busobj = bookingobj.get().getScheduleobj().getBusobj();
		if (bookingobj != null && bookingobj.get().getBookStatus().equalsIgnoreCase("BOOKED")) {
			if (busobj != null) {

				int seats = busobj.getBookedSeats();
				busobj.setBookedSeats(busobj.getBookedSeats() - 1);
				// updating the seat info of that bus
				busobj.setRemainingSeats(busobj.getRemainingSeats() + 1);

				// refunding
				double refundAmount = bookingobj.get().getEmpobj().getWalletBalance()
						+ bookingobj.get().getScheduleobj().getFareAmount();
				Booking bookingOg = bookingobj.get();
				Employee employee = bookingOg.getEmpobj();
				employee.setWalletBalance(refundAmount);
				this.empRepo.save(employee);
				bookingOg.setEmpobj(employee);
				this.bookingRepo.save(bookingOg);

				// Waiting List section start here
				System.out.println("existing");
				this.waitingService.getFirstBooking(busobj);
				
				// deleting it from db
				/* bookingRepo.deleteById(bookingId); */
				Booking delbook = bookingRepo.getById(bookingId);
				
				System.out.println("POST /cancelTicket delbook (Before Cancellation) : " + delbook);
				
				delbook.setBookStatus(Status.CANCELLED.toString());
				
				System.out.println("POST /cancelTicket delbook (After Cancellation) : " + delbook);
				
				bookingRepo.save(delbook);
				res = true;
			}
			//If cancelled show a popup
		}

		return res;
	}

	@Override
	public boolean updateSeat(Long busId, int seats) {
		Bus busobj = busRepo.getById(busId);
		if (busobj != null) {
			busobj.setRemainingSeats(busobj.getRemainingSeats() + seats);
			return true;
		}

		return false;
	}
}
