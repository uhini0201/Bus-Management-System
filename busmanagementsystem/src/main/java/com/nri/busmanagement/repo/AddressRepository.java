package com.nri.busmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nri.busmanagement.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
