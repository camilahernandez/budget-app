package com.springboot.budgetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.budgetapp.entity.UserBudget;
import com.springboot.budgetapp.entity.UserEntity;
import com.springboot.budgetapp.service.UserTransactionServices;

@RestController
@RequestMapping("api/users")
public class UserController {

	UserTransactionServices userTransactionService;
	
	@Autowired
	public UserController(UserTransactionServices userTransactionService) {
		this.userTransactionService = userTransactionService;
	}
	
	@PostMapping
	public void createUser(@RequestBody UserEntity user) {
		userTransactionService.createUser(user);
	}
	
	@PostMapping(path =  "/{userId}/createBudget")
	public void addBudget(@PathVariable(name = "userId") Long userId,@RequestBody UserBudget budget){
		userTransactionService.createBudget(userId, budget);
	}
	
	
}
