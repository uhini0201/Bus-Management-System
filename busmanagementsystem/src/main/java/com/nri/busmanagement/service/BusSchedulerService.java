package com.nri.busmanagement.service;

import org.springframework.stereotype.Service;

@Service
public class BusSchedulerService {
	/*
	 * The main task of the Scheduler class is to return a list of scheduled buses according to the query
	 * 
	 * The query consists of SOURCE, DESTINATION and DATE
	 * 
	 * Working
	 * -------
	 * 
	 * The BusSchedulerService sends the above parameters to the RouteDispatcher Class, which filters available routes
	 * 
	 */
}
