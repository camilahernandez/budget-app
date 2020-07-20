package com.springboot.budgetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.budgetapp.entity.UserTransactionEntity;
import com.springboot.budgetapp.service.UserTransactionServices;

@RestController
@RequestMapping(path = "api/transactions")
public class TransactionController {
	
	UserTransactionServices userTransactionService;
	
	@Autowired
	public TransactionController(UserTransactionServices userTransactionService) {
		this.userTransactionService = userTransactionService;
		
	}

	@GetMapping(path = "/{userId}")
	public List<UserTransactionEntity> getHistoryTransactions(@PathVariable("userId") Long userId){
		return userTransactionService.getTransactionsForUser(userId);
	}
	
	@PostMapping(path = "/{userId}")
	public void createTransaction(@PathVariable("userId") Long userId, @RequestBody UserTransactionEntity transaction) {
		userTransactionService.createTransaction(userId, transaction);
	}
}
