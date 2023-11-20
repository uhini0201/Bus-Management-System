package com.nri.busmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nri.busmanagement.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{

}
