/*
 * package com.nri.busmanagement.controller;
 * 
 * import java.time.LocalDate; import java.time.LocalTime; import
 * java.time.ZoneId; import java.util.ArrayList; import java.util.Date; import
 * java.util.List; import java.util.Optional; import
 * java.util.stream.Collectors;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.data.jpa.repository.Query; import
 * org.springframework.security.core.context.SecurityContextHolder; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * com.nri.busmanagement.model.BookDetails; import
 * com.nri.busmanagement.model.Booking; import com.nri.busmanagement.model.Bus;
 * import com.nri.busmanagement.model.CustomUserDetails; import
 * com.nri.busmanagement.model.Employee; import
 * com.nri.busmanagement.model.Location; import
 * com.nri.busmanagement.model.Route; import
 * com.nri.busmanagement.model.Schedule; import
 * com.nri.busmanagement.model.Status; import
 * com.nri.busmanagement.model.UserAuthentication; import
 * com.nri.busmanagement.service.AdminService; import
 * com.nri.busmanagement.service.BookingService; import
 * com.nri.busmanagement.service.BookingServiceImpl; import
 * com.nri.busmanagement.service.BusService; import
 * com.nri.busmanagement.service.BusServiceImpl; import
 * com.nri.busmanagement.service.ConfigureRoute; import
 * com.nri.busmanagement.service.ConfigureSchedule; import
 * com.nri.busmanagement.service.EmployeeServiceImpl; import
 * com.nri.busmanagement.service.LocationService;
 * 
 * @Controller
 * 
 * @RequestMapping("/user") public class BookingController {
 * 
 * 
 * @Autowired private AdminService adminservice;
 * 
 * @Autowired private BookingServiceImpl bookservice;
 * 
 * @Autowired private BusServiceImpl busservice;
 * 
 * @Autowired private EmployeeServiceImpl empservice;
 * 
 * @Autowired private ConfigureRoute routeServ;
 * 
 * @Autowired private LocationService locServ;
 * 
 * @Autowired private ConfigureSchedule scheduleServ;
 * 
 * List<Employee> lemp = new ArrayList<>();
 * 
 * BookDetails globaldet; List<Location> lloc; List<Route> lroute = new
 * ArrayList<>(); List<Schedule> schedules = new ArrayList<>();
 * 
 * Employee emp; Schedule scheduleobj;
 * 
 * Booking bookingModel; Booking globBookingModel;
 * 
 * @GetMapping("/getBuses") public String getBuses(Model model) { BookDetails
 * det = new BookDetails();
 * 
 * lloc = locServ.getAllLocations();
 * 
 * model.addAttribute("det", det); model.addAttribute("lloc", lloc); return
 * "booking"; }
 * 
 * @PostMapping("/getBuses") public String getBuses(@RequestBody BookDetails
 * det, Model model) { schedules.clear(); globaldet = det;
 * 
 * lroute = routeServ.getRoute(det.getSrc());
 * 
 * for (Route route : lroute) {
 * schedules.addAll(scheduleServ.getSchedulefromRoute(route)); }
 * 
 * System.out.println("Schedules from getBuses POST");
 * System.out.println(schedules);
 * 
 * return "redirect:/user/getReservation"; // return "Bus"; }
 * 
 * @GetMapping(path = "/getReservation") public String getReservation(Model
 * model) { System.out.println("Schedules from getReservation GET");
 * System.out.println(schedules); model.addAttribute("schedules", schedules);
 * 
 * return "Bus"; }
 * 
 * @PostMapping("/getReservation") public String getReservation(@RequestBody
 * Booking booking, Model model) { model.addAttribute(booking);
 * System.out.println(model); return "redirect:/user/bookingDash"; }
 * 
 * @GetMapping("/ticket") public String createBooking(Model model) {
 * schedules.clear(); // Booking bookingModel = new Booking();
 * System.out.println("GET /ticket Global Booking Model: " + globBookingModel);
 * model.addAttribute("bookingModel", globBookingModel);
 * 
 * return "ticket"; }
 * 
 * @PostMapping("/book") public String createBooking(@RequestBody String busId,
 * Model model) { String busid = busId.substring(1, busId.length() - 1);
 * 
 * Bus busobj = this.busservice.getABusById(busservice.getAllBuses().stream()
 * .filter(bobj ->
 * bobj.getBusNumberPlate().equals(busid)).findFirst().get().getBusId()).get();
 * 
 * for (Schedule sch : schedules) { if
 * (sch.getBusobj().getBusNumberPlate().equals(busid)) { scheduleobj = sch;
 * break; } }
 * 
 * List<Employee> lemp = this.empservice.getAllEmployees();
 * 
 * CustomUserDetails user = (CustomUserDetails)
 * SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
 * String currUserEmail = user.getUsername();
 * 
 * emp = lemp.stream().filter(eobj ->
 * eobj.getempEmail().equalsIgnoreCase(currUserEmail)).findAny().get();
 * 
 * List<Booking> lbook = bookservice.getAllBookings();
 * 
 * for (Booking book : lbook) { if
 * (book.getEmpobj().getempEmail().equals(currUserEmail)) {
 * 
 * System.out.println("Error"); return "redirect:/user/sendMessage"; } }
 * 
 * 
 * String source = globaldet.getSrc(); String destination = globaldet.getDst();
 * LocalDate travelDate=globaldet.getDate().toInstant()
 * .atZone(ZoneId.systemDefault()) .toLocalDate().now();
 * 
 * LocalDate bookDate = LocalDate.now();
 * 
 * String currTime = LocalTime.now().toString(); Double bookTime =
 * Double.parseDouble(currTime.substring(0,2) + "." + currTime.substring(3,5));
 * 
 * String bookStatus = Status.BOOKED.toString();
 * 
 * Double fareAmount = scheduleobj.getFareAmount(); if (emp.getWalletBalance() <
 * fareAmount) { bookStatus = Status.CANCELLED.toString(); }
 * 
 * bookingModel = new Booking(Long.parseLong("0"), travelDate, bookTime,
 * bookStatus, fareAmount, source, destination, scheduleobj, emp);
 * 
 * globBookingModel = this.bookservice.bookTicket(bookingModel);
 * 
 * return "redirect:/user/ticket";
 * 
 * }
 * 
 * @GetMapping("/getBook/{bookId}") public Optional<Booking>
 * viewBooking(@PathVariable Long bookId) { return
 * this.bookservice.getBookingById(bookId); }
 * 
 * @GetMapping("/getBooks") public List<Booking> viewBookings() { return
 * this.bookservice.getAllBookings(); }
 * 
 * @PutMapping("/updateBooking") public String updateBooking(@RequestBody
 * Booking booking) { this.bookservice.updateBooking(booking); return
 * "Booking got updated"; }
 * 
 * 
 * 
 * @PostMapping("/cancelBook") public String cancelBooking(@RequestBody String
 * bookId) { String bookid = bookId.substring(1, bookId.length() - 1); if
 * (this.bookservice.cancelTicket(Long.parseLong(bookid))) { return
 * "redirect:/user/bookingDash"; } else { return "Some error occured."; }
 * 
 * }
 * 
 * @GetMapping("/bookingDash") public String allBookings(Model model) {
 * CustomUserDetails user = (CustomUserDetails)
 * SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
 * String currUserEmail = user.getUsername();
 * 
 * List<Booking> lbookings = this.bookservice.getAllBookings().stream()
 * .filter(bobj ->
 * bobj.getEmpobj().getempEmail().equals(currUserEmail)).collect(Collectors.
 * toList()); model.addAttribute("lbookings", lbookings);
 * 
 * System.out.println("All Bookings of current user");
 * System.out.println(lbookings);
 * 
 * return "book"; }
 * 
 * }
 */

package com.nri.busmanagement.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nri.busmanagement.model.BookDetails;
import com.nri.busmanagement.model.Booking;
import com.nri.busmanagement.model.Bus;
import com.nri.busmanagement.model.CustomUserDetails;
import com.nri.busmanagement.model.Employee;
import com.nri.busmanagement.model.Location;
import com.nri.busmanagement.model.Route;
import com.nri.busmanagement.model.Schedule;
import com.nri.busmanagement.model.Status;
import com.nri.busmanagement.model.UserAuthentication;
import com.nri.busmanagement.repo.LocationRepository;
import com.nri.busmanagement.service.AdminService;
import com.nri.busmanagement.service.BookingService;
import com.nri.busmanagement.service.BookingServiceImpl;
import com.nri.busmanagement.service.BusService;
import com.nri.busmanagement.service.BusServiceImpl;
import com.nri.busmanagement.service.ConfigureRoute;
import com.nri.busmanagement.service.ConfigureSchedule;
import com.nri.busmanagement.service.EmployeeServiceImpl;
import com.nri.busmanagement.service.LocationService;

@Controller
@RequestMapping("/user")
public class BookingController {

	/*
	 * @Autowired private AdminService adminservice;
	 */
	@Autowired
	private BookingServiceImpl bookservice;
	@Autowired
	private BusServiceImpl busservice;
	@Autowired
	private EmployeeServiceImpl empservice;
	@Autowired
	private ConfigureRoute routeServ;
	@Autowired
	private LocationRepository locServ;
	@Autowired
	private ConfigureSchedule scheduleServ;

	List<Employee> lemp = new ArrayList<>();
	List<Location> lloc;
	BookDetails globaldet;
	List<Route> lroute = new ArrayList<>();
	List<Schedule> schedules = new ArrayList<>();

	Employee emp;
	Schedule scheduleobj;

	Booking bookingModel;
	Booking globBookingModel;

	@GetMapping("/ticket")
	public String getTicket(Model model) {
		model.addAttribute("ticket", globBookingModel);

		return "ticket";
	}

	@GetMapping("/getBuses")
	public String getBuses(Model model) {
		BookDetails det = new BookDetails();
		lloc = locServ.findAll();

		model.addAttribute("det", det);
		model.addAttribute("lloc", lloc);
		System.out.println(lloc);
		model.addAttribute("det", det);
		return "booking";
	}

	@PostMapping("/getBuses")
	public String getBuses(@RequestBody BookDetails det, Model model) {
		schedules.clear();
		globaldet = det;

		lroute = routeServ.getRoute(det.getSrc());

		for (Route route : lroute) {
			schedules.addAll(scheduleServ.getSchedulefromRoute(route));
		}

		System.out.println("Schedules from getBuses POST");
		System.out.println(schedules);

		return "redirect:/user/getReservation";
//		return "Bus";
	}

	@GetMapping(path = "/getReservation")
	public String getReservation(Model model) {
		System.out.println("Schedules from getReservation GET");
		System.out.println(schedules);
		model.addAttribute("schedules", schedules);

		return "Bus";
	}

	@PostMapping("/getReservation")
	public String getReservation(@RequestBody Booking booking, Model model) {
		model.addAttribute(booking);
		System.out.println(model);
		return "redirect:/user/bookingDash";
	}

	@GetMapping("/book")
	public String createBooking(Model model) {
		/* schedules.clear(); */
		// Booking bookingModel = new Booking();
		System.out.println("GET /ticket Global Booking Model: " + globBookingModel);
		model.addAttribute("ticket", globBookingModel);

		return "redirect:/user/ticket";
	}

	@PostMapping("/book")
	public String createBooking(@RequestBody String schId, Model model) {
		String schid = schId.substring(1, schId.length() - 1);
		Long scheduleId=Long.parseLong(schid);
		Schedule schobj=this.scheduleServ.getSchedule(scheduleServ.getAllSchedules().stream().filter(scobj->scobj.getScheduleId().equals(scheduleId)).findFirst().get().getScheduleId()).get();
		Bus busobj=schobj.getBusobj();
		System.out.println(busobj);
		/*
		 * Bus busobj = this.busservice.getABusById(busservice.getAllBuses().stream()
		 * .filter(bobj ->
		 * bobj.getBusNumberPlate().equals(busid)).findFirst().get().getBusId()).get();
		 */
		scheduleobj=schobj;

		/*
		 * for (Schedule sch : schedules) { if
		 * (sch.getBusobj().getBusNumberPlate().equals(busid)) { scheduleobj = sch;
		 * break; } }
		 */

		List<Employee> lemp = this.empservice.getAllEmployees();

		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String currUserEmail = user.getUsername();

		emp = lemp.stream().filter(eobj -> eobj.getempEmail().equalsIgnoreCase(currUserEmail)).findAny().get();

		List<Booking> lbook = bookservice.getAllBookings();
		/*
		 * for (Booking book : lbook) { if
		 * (book.getEmpobj().getempEmail().equals(currUserEmail)) {
		 * 
		 * System.out.println("Error"); return "redirect:/user/sendMessage"; } }
		 */

		String source = globaldet.getSrc();
		String destination = globaldet.getDst();
		LocalDate travelDate = globaldet.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().now();

		LocalDate bookDate = LocalDate.now();

		String currTime = LocalTime.now().toString();
		Double bookTime = Double.parseDouble(currTime.substring(0, 2) + "." + currTime.substring(3, 5));

		String bookStatus = Status.BOOKED.toString();

		Double fareAmount = scheduleobj.getFareAmount();
		if (emp.getWalletBalance() < fareAmount) {
			bookStatus = Status.CANCELLED.toString();
		}

		bookingModel = new Booking(Long.parseLong("0"), travelDate, bookTime, bookStatus, fareAmount, source,
				destination, scheduleobj, emp);

		globBookingModel = this.bookservice.bookTicket(bookingModel);
		
		System.out.println("Fetch executed");

		return "redirect:/user/ticket";

	}

	@GetMapping("/getBook/{bookId}")
	public Optional<Booking> viewBooking(@PathVariable Long bookId) {
		return this.bookservice.getBookingById(bookId);
	}

	@GetMapping("/getBooks")
	public List<Booking> viewBookings() {
		return this.bookservice.getAllBookings();
	}

	@PutMapping("/updateBooking")
	public String updateBooking(@RequestBody Booking booking) {
		this.bookservice.updateBooking(booking);
		return "Booking got updated";
	}

	@PostMapping("/cancelBook")
	public String cancelBooking(@RequestBody String bookId) {
		String bookid = bookId.substring(1, bookId.length() - 1);
		System.out.println("POST /cancelBook BookID: " +bookid);
		if (this.bookservice.cancelTicket(Long.parseLong(bookid))) {
			return "redirect:/user/bookingDash";
		} else {
			return "Some error occured.";
		}

	}

	@GetMapping("/bookingDash")
	public String allBookings(Model model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String currUserEmail = user.getUsername();

		List<Booking> lbookings = this.bookservice.getAllBookings().stream()
				.filter(bobj -> bobj.getEmpobj().getempEmail().equals(currUserEmail)).collect(Collectors.toList());
		model.addAttribute("lbookings", lbookings);

		System.out.println("All Bookings of current user");
		System.out.println(lbookings);

		return "book";
	}

}
