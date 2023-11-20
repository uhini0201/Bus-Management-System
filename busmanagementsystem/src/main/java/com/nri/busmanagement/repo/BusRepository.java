package com.nri.busmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nri.busmanagement.model.Bus;


@Repository
public interface BusRepository extends JpaRepository<Bus, Long>{


}
