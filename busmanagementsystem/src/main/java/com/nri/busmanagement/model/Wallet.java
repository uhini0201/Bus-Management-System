package com.nri.busmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@NotBlank(message = "Name can't be blank")
	@Size(min=2,max=30)
	private String accountName;
	
	@Size(min=2,max=30)
	private String accountNumber;
	
	@Size(max=30)
	private String description;
	
	@Min(1)
	@Max(3)
	private Integer priority;//1:High 2:medium 3:low
	
	private Double currentBalance;
	
	
	public Wallet(Long id, @NotBlank(message = "Name can't be blank") @Size(min = 2, max = 30) String accountName,
			@Size(min = 2, max = 30) String accountNumber, @Size(max = 30) String description,
			@Min(1) @Max(3) Integer priority, Double currentBalance) {
		super();
		this.id = id;
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.description = description;
		this.priority = priority;
		this.currentBalance = currentBalance;
	}


	public Wallet() {
		super();
	}

	//When the wallet object is created first time then at that time the current balance is 0; which can be done using @PrePersist annotation
	@PrePersist void setBalance() {
		this.currentBalance = 0.0;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getPriority() {
		return priority;
	}


	public void setPriority(Integer priority) {
		this.priority = priority;
	}


	public Double getCurrentBalance() {
		return currentBalance;
	}


	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}


	@Override
	public String toString() {
		return "Wallet [id=" + id + ", accountName=" + accountName + ", accountNumber=" + accountNumber
				+ ", description=" + description + ", priority=" + priority + ", currentBalance=" + currentBalance
				+ "]";
	}
}
