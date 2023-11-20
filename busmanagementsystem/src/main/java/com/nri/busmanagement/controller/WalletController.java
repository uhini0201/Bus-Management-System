package com.nri.busmanagement.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nri.busmanagement.model.Wallet;
import com.nri.busmanagement.service.ValidationErrorService;
import com.nri.busmanagement.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {
	
	@Autowired
	private WalletService walletservice;
	
	@Autowired
	private ValidationErrorService vlaidateService;
	
	@PostMapping("/createOrUpdate")
	public ResponseEntity<?> createWallet(@RequestBody Wallet wallet ,BindingResult result){
		ResponseEntity errors=vlaidateService.validate(result);
		if(errors!=null) {
			return errors;
		}
		Wallet walletSaved=walletservice.createOrUpdate(wallet);
		return new ResponseEntity<Wallet>(walletSaved,HttpStatus.CREATED);
	}
	

}
