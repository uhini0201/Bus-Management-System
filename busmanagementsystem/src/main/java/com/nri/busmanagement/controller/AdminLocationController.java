package com.nri.busmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nri.busmanagement.model.Location;
import com.nri.busmanagement.service.LocationService;

@Controller
@RequestMapping("/admin")
public class AdminLocationController {

	@Autowired
	private LocationService locationService;

	private Location dbLocation;

	@GetMapping(path = "/createLocation")
	public String addLocation(Model model) {
		Location locModel = new Location();
		model.addAttribute("locModel", locModel);
		return "admin_location_add";
	}

	@PostMapping(path = "/createLocation")
	public String createLocation(@ModelAttribute("locModel") Location locModel, Model model) {
//		locModel.setLocationId((long) 0);
		System.out.println(locModel);
		this.locationService.addLocation(locModel);

		return "redirect:/admin/viewAllLocation";
	}

	@GetMapping(path = "/updateLocation")
	public ModelAndView updateLocation(@RequestParam Long locationId) {

		ModelAndView mav = new ModelAndView("admin_location_update");

		Location location = locationService.getLocation(locationId).get();
		mav.addObject("locModel", location);

		this.dbLocation = location;

		System.out.println("Location from Update: " + location);

		return mav;
	}

	@PostMapping(path = "/updateLocation")
	public String updateLocation(@ModelAttribute Location locModel) {
		System.out.println(locModel);

		locModel.setLocationId(dbLocation.getLocationId());
		locModel.getAddressobj().setAddressId(dbLocation.getAddressobj().getAddressId());

		this.locationService.updateLocation(locModel);
		return "redirect:/admin/viewAllLocation";
	}

	@GetMapping(path = "/viewAllLocation")
	public String getAllLocations(Model model) {

		List<Location> lloc = locationService.getAllLocations();
		model.addAttribute("locations", lloc);

		return "admin_location_dash";
	}

	@GetMapping(path = "/removeLocation")
	public String deleteLocation(@RequestParam Long locationId) {
		this.locationService.deleteLocation(locationId);
		return "redirect:/admin/viewAllLocation";
	}

	@GetMapping(path = "/getLocationById/{locationId}")
	public Optional<Location> getLocation(@RequestParam("locationId") Long locationId) {
		return this.locationService.getLocation(locationId);
	}

	/*******************************************************************************************************/
	/*
	 * @PostMapping(path="/updateLocation", consumes="application/json") public
	 * String updateLocation(@ModelAttribute Location locModel) {
	 * this.locationService.updateLocation(locModel); return
	 * "redirect:/getAllLocations"; }
	 * 
	 * @GetMapping(path="/getLocationById/{locationId}") public Optional<Location>
	 * getLocation(@PathVariable("locationId") Long locationId){ return
	 * this.locationService.getLocation(locationId); }
	 * 
	 * @GetMapping(path="/getLocationByBusStopName/{busStop}") public List<Location>
	 * getLocation(@PathVariable("busStop") String busStop){ return
	 * this.locationService.getLocation(busStop); }
	 * 
	 * @GetMapping(path="/getLocationByTimes/{arrivalTime}/{departureTime}") public
	 * List<Location>
	 * getLocationByArrivalAndDepartureTime(@PathVariable("arrivalTime") Double
	 * arrivalTime,@PathVariable("departureTime") Double departureTime){ return
	 * this.locationService.getLocationByArrivalAndDepartureTime(arrivalTime,
	 * departureTime); }
	 */
}
