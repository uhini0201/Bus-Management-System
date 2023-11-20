package com.nri.busmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nri.busmanagement.model.IdLocationParser;
import com.nri.busmanagement.model.Location;
import com.nri.busmanagement.model.Route;
import com.nri.busmanagement.service.ConfigureRoute;
import com.nri.busmanagement.service.LocationService;

@Controller
@RequestMapping("/admin")
public class RouteController {
	
	@Autowired
	private ConfigureRoute configRoute;
	
	@Autowired
	private LocationService locServ;
	
	@GetMapping(path="/createRoute")
	public String addRoute(Model model) {
		model.addAttribute("locations", locServ.getAllLocations());
		return "admin_route_add";
	}
	
	@PostMapping(path="/createRoute")
	public String addRoute(@RequestBody IdLocationParser idLocParser, Model model) {
		System.out.println(idLocParser);
			
		List<Location> listLocations = new ArrayList<>();
		for (String loc : idLocParser.getPickUpPoints()) {
			listLocations.add(locServ.getLocation(Long.parseLong(loc)).get());
		}
		
		Route route = new Route((long)0, listLocations, idLocParser.getRouteName());
		
		this.configRoute.addRoute(route);
		
		return "redirect:/admin/viewAllRoute";
	}
	
	@GetMapping(path="/getRouteById/{routeId}")
	public Optional<Route> getRouteById(@PathVariable("routeId") Long routeId){
		return this.configRoute.getRoute(routeId);
	}
	
	@GetMapping(path="/getRoutesByBusStop/{busStop}")
	public List<Route> getRoutesByBusStop(@PathVariable("busStop") String busStop){
		return this.configRoute.getRoute(busStop);
	}
	
	@GetMapping(path="/viewAllRoute")
	public String getAllRoutes(Model model){
//		List<Route> routes=this.configRoute.getAllRoutes();
//		model.addAttribute("routes", routes);
//		System.out.println(routes);
//		return "admin_route_dash";
		
		List<Route> routeList=this.configRoute.getAllRoutes();
		List<String> busStopListOfAllRoutes=new ArrayList();
		for(Route route:routeList) {
			busStopListOfAllRoutes.add(this.configRoute.getStringPickUpPoints(route.getRouteId()));
		}
		model.addAttribute("routes",routeList);
		model.addAttribute("busStopListOfAllRoutes", busStopListOfAllRoutes);
		return "admin_route_dash";

		
	}
	
	@PutMapping(path="/updateRoute",consumes="application/json")
	public String updateRoute(@RequestBody Route route) {
		return this.configRoute.updateRoute(route, route.getRouteId());
	}
	
	@GetMapping(path="/removeRoute")
	public String removeRoute(@RequestParam("routeId") long routeId) {
		this.configRoute.deleteRoute(routeId);
		return "redirect:/admin/viewAllRoute";
	}
	


}
