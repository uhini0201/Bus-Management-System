package com.nri.busmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nri.busmanagement.model.Wallet;
import com.nri.busmanagement.repo.WalletRepository;

import javax.validation.constraints.AssertFalse.List;

@Service
public class WalletService {
	
	@Autowired
	private WalletRepository walletRepository;
	
	public Wallet createOrUpdate(Wallet wallet) {
		if(wallet.getId()==null) {
			walletRepository.save(wallet);
		}else {
			walletRepository.save(wallet);
		}
		return wallet;
	}
	
	public boolean delete(Long id) {
		Optional<Wallet> wallet=walletRepository.findById(id);
		if(wallet.isPresent()) {
			walletRepository.delete(wallet.get());
			return true;
		}
		
		return false;
	}

}
