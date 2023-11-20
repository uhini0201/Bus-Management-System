package com.nri.busmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nri.busmanagement.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {

}
