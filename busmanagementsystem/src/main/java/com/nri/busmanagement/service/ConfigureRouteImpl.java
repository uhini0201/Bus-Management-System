package com.nri.busmanagement.service;

import java.util.List;
import java.util.NoSuchElementException;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nri.busmanagement.model.Location;
import com.nri.busmanagement.model.Route;
import com.nri.busmanagement.repo.RouteRepository;

/*
 * The objective of this class is to introduce all kinds of configuration to a route by the Administrator:
 * 
 * 1) add new route
 * 2) update existing route 
 * 3) view existing route(s) [Filter via RouteId or any pickUpPoint(busStop)]
 * 4) view all active routes
 * 5) delete a route
 * 
 */

@Service

public class ConfigureRouteImpl implements ConfigureRoute {
	
	@Autowired
	private RouteRepository routeRepo;

	@Override
	public String addRoute(Route route) {
		if (route.getRouteId()!=null && route.getPickUpPoints()!=null
				&& route.getRouteName()!=null) {
			this.routeRepo.save(route);
			return "Successfully added a route";
		}
		return "Either one or multiple fields are missing! Please send a valid route to add to the database!";
	}

	@Override
	public String updateRoute(Route updatedRoute, Long routeId) {

		if (updatedRoute!=null && routeId!=null) {
			try {
				Route existingRoute = this.getRoute(routeId).get();
				if (updatedRoute.getRouteName()!=null && updatedRoute.getPickUpPoints()!=null
						&& updatedRoute.getRouteId()!=null) {
					existingRoute.setRouteId(updatedRoute.getRouteId());
					existingRoute.setPickUpPoints(updatedRoute.getPickUpPoints());
					existingRoute.setRouteName(updatedRoute.getRouteName());
					this.routeRepo.save(existingRoute);

					return "Successfully updated the Route!";
				} else {
					return "Either one or multiple fields are missing!";
				}
			} catch (NoSuchElementException ex) {
				return "No route found with this Id";
			}
		}
		return "Either or both the updatedRoute or routeId is empty/null";
	}

	@Override
	public Optional<Route> getRoute(Long routeId) {
		return this.routeRepo.findById(routeId);
	}

	@Override
	public List<Route> getRoute(String busStop) {

		List<Route> listRoute = this.routeRepo.findAll();
		List<Route> resultRoutes = listRoute.stream()
				.filter(route -> route.getPickUpPoints().stream()
						.anyMatch(location -> location.getAddressobj().getBusStopName().equals(busStop)))
				.collect(Collectors.toList());

		return resultRoutes;

	}

	@Override
	public List<Route> getAllRoutes() {
		return this.routeRepo.findAll();
	}

	@Override
	public String deleteRoute(Long routeId) {
		if (routeId!=null) {
			try {
				Route existingRoute = this.getRoute(routeId).get();
				this.routeRepo.deleteById(existingRoute.getRouteId());
				return "Successfully deleted the route!";
			}
			catch (NoSuchElementException ex) {
				return "Enter a valid routeId to delete the route";
			}
		}
		return "Please enter the routeId field";
	}
	@Override
	public String getStringPickUpPoints(Long routeId) {
		String res="";
		Route route=this.getRoute(routeId).get();
		for (Location location : route.getPickUpPoints()) {
			res=res+location.getAddressobj().getBusStopName()+" ";
		}
		return res;
	}

}
