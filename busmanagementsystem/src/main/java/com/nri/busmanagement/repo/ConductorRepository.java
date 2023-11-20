package com.nri.busmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nri.busmanagement.model.Conductor;


@Repository
public interface ConductorRepository extends JpaRepository<Conductor, String>{

}
