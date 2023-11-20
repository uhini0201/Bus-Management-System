/**
 * 
 */
package com.nri.busmanagement.service;

import java.util.List;
import java.util.Optional;

import com.nri.busmanagement.model.Conductor;


public interface ConductorService {
	public List<Conductor> getAllConductor();
	public String addConductor(Conductor conductor);
	public String deleteConductor(String cId);
	public String updateConductor(Conductor conductor);
	public Optional<Conductor> getConductorById(String cId);
	public List<Conductor> getConductorByName(String conductorName);
}
