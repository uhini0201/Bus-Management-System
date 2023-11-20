package com.nri.busmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nri.busmanagement.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

}
