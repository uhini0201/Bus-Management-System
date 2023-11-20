package com.nri.busmanagement.service;

import java.util.List;
import java.util.Optional;

import com.nri.busmanagement.model.Route;

public interface ConfigureRoute {
	
	public String addRoute(Route route);
	public String updateRoute(Route updatedRoute, Long routeId);
	public Optional<Route> getRoute(Long routeId);
	public List<Route> getRoute(String busStop);
	public List<Route> getAllRoutes();
	public String deleteRoute(Long routeId);
	public String getStringPickUpPoints(Long routeId);
	
}
