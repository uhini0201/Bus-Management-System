package com.nri.busmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nri.busmanagement.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

}
