package com.nri.busmanagement.service;

import java.util.List;
import java.util.NoSuchElementException;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nri.busmanagement.model.Conductor;
import com.nri.busmanagement.repo.ConductorRepository;

@Service
public class ConductorServiceImpl implements ConductorService {

	@Autowired
	private ConductorRepository conductorRepository;
	
	List<Conductor> conductorList ;
	List<Conductor> resultList;
	
	@Override
	public List<Conductor> getAllConductor() {
		return this.conductorRepository.findAll();
	}

	@Override
	public String addConductor(Conductor conductor) {
		if(conductor.getCondName()!=null && 
				conductor.getCondPhone()!=null && conductor.getCondEmail()!=null && 
				conductor.getCondSal()!=null) {
			this.conductorRepository.save(conductor);
			return "The conductor is added successfully";
		}
		return "Either one or multiple fields are missing! Please send a valid conductor details to add to the database!";
	}

	@Override
	public String deleteConductor(String id) {
		
		if(id!=null) {
			try {
				Conductor existingConductor = this.getConductorById(id).get();
				this.conductorRepository.deleteById(existingConductor.getCondEmail());
				return "Successfully deleted the Conductor";
			}catch(NoSuchElementException ex) {
				return "No conductor found with this ID";
			}
		}
		return "Please enter the valid conductor id";
	}

	@Override
	public String updateConductor(Conductor conductor) {
		
		if(conductor!=null && conductor.getCondEmail()!=null) {
			try {
				Conductor existingConductor = this.conductorRepository.findById(conductor.getCondEmail()).get();
				if(conductor.getCondEmail()!=null && conductor.getCondName()!=null && 
						conductor.getCondPhone()!=null && conductor.getCondEmail()!=null && 
						conductor.getCondSal()!=null ) {
					
					existingConductor.setCondEmail(conductor.getCondEmail());
					existingConductor.setCondName(conductor.getCondName());
					existingConductor.setCondPhone(conductor.getCondPhone());
					existingConductor.setCondSal(conductor.getCondSal());
					
					this.conductorRepository.save(existingConductor);
					return "Successfully updated the conductor";
					
				}else {
					return "Either one or multiple feilds are missing";
				}
			} catch(NoSuchElementException ex) {
				return "No driver found with this ID";
			}
		}
		return "Either or both the conductor details or id is empty or null";
	}
	
	@Override
	public Optional<Conductor> getConductorById(String cId) {
		return this.conductorRepository.findById(cId);
	}
	
	@Override
	public List<Conductor> getConductorByName(String conductorName) {
		conductorList=this.getAllConductor();
		resultList=null;
		if(conductorName!=null) {
			resultList=conductorList.stream().filter(conductor -> conductor.getCondName().equals(conductorName)).collect(Collectors.toList());
		}
		
		return resultList;
	}
	
	

}
