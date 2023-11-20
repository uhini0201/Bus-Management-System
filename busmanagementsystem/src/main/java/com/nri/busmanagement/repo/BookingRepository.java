package com.nri.busmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nri.busmanagement.model.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

}
