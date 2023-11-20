//package com.nri.busmanagement.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.nri.busmanagement.model.BookDetails;
//import com.nri.busmanagement.model.Booking;
//import com.nri.busmanagement.model.Bus;
//import com.nri.busmanagement.model.Route;
//import com.nri.busmanagement.model.Schedule;
//import com.nri.busmanagement.service.AddressService;
//import com.nri.busmanagement.service.BookingService;
//import com.nri.busmanagement.service.ConfigureRoute;
//import com.nri.busmanagement.service.ConfigureSchedule;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminBookingController {
//
//	@Autowired
//	private BookingService bookServ;
//
//	@GetMapping("/viewAllBooking")
//    public String viewBookings(Model model, @RequestParam(required=false) String status) {
//        if (status == null) {
//            model.addAttribute("lbook", this.bookServ.getAllBookings());
//            return "admin_booking_dash";
//        }
//        if(status.equalsIgnoreCase("All")) {
//            List<Booking> lbook = this.bookServ.getAllBookings();
//            System.out.println(lbook);
//            model.addAttribute("lbook", lbook);
//            return "admin_booking_dash";
//        }
//        List<Booking> filteredBook=this.bookServ.getAllBookings().stream().filter(obj->obj.getBookStatus().equalsIgnoreCase(status))
//                .collect(Collectors.toList());
//        model.addAttribute("lbook", filteredBook);
//        return "admin_booking_dash";
//    }
//
//}



package com.nri.busmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nri.busmanagement.model.BookDetails;
import com.nri.busmanagement.model.Booking;
import com.nri.busmanagement.model.Bus;
import com.nri.busmanagement.model.Route;
import com.nri.busmanagement.model.Schedule;
import com.nri.busmanagement.service.AddressService;
import com.nri.busmanagement.service.BookingService;
import com.nri.busmanagement.service.ConfigureRoute;
import com.nri.busmanagement.service.ConfigureSchedule;

@Controller
@RequestMapping("/admin")
public class AdminBookingController {

    @Autowired
    private BookingService bookServ;
    
    
    @GetMapping("/viewAllBooking")
    public String viewBookings(Model model, @RequestParam(required=false) String status) {
        if (status == null) {
            model.addAttribute("lbook", this.bookServ.getAllBookings());
            return "admin_booking_dash";
        }
        if(status.equalsIgnoreCase("All")) {
            List<Booking> lbook = this.bookServ.getAllBookings();
            System.out.println(lbook);
            model.addAttribute("lbook", lbook);
            return "admin_booking_dash";
        }
        List<Booking> filteredBook=this.bookServ.getAllBookings().stream().filter(obj->obj.getBookStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
        model.addAttribute("lbook", filteredBook);
        return "admin_booking_dash";
    }
}