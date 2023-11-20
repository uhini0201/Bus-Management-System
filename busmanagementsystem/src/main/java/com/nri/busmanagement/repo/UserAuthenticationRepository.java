package com.nri.busmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nri.busmanagement.model.UserAuthentication;

public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, String>{

}
