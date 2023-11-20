package com.nri.busmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nri.busmanagement.model.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, String> {

}
