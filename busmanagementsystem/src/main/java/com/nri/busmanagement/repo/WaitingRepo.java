package com.nri.busmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nri.busmanagement.model.WaitingList;

public interface WaitingRepo extends JpaRepository<WaitingList, Long> {

}
