package com.springboot.budgetapp.service;

import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.budgetapp.entity.UserBudget;
import com.springboot.budgetapp.entity.UserEntity;
import com.springboot.budgetapp.entity.UserTransactionEntity;
import com.springboot.budgetapp.exception.ApplicationRequestException;
import com.springboot.budgetapp.repository.UserBudgetRepository;
import com.springboot.budgetapp.repository.UserRepository;
import com.springboot.budgetapp.repository.UserTransactionRepository;
@Service
public class UserTransactionServices {
	
	UserTransactionRepository userTransactionRepository;
	UserRepository userRepository;
	UserBudgetRepository userBudgetRepository;
	
	@Autowired
	public UserTransactionServices(UserTransactionRepository userTransactionRepository,UserRepository userRepository,UserBudgetRepository userBudgetRepository) {
		this.userTransactionRepository = userTransactionRepository;
		this.userRepository = userRepository;
		this.userBudgetRepository = userBudgetRepository;
	}
	
	public List<UserTransactionEntity> getTransactionsForUser(Long userId){
		return userTransactionRepository.findByUser(userId);
	}
	
	public void createTransaction(Long userId, UserTransactionEntity transaction) {
		UserEntity user = userRepository.findById(userId).orElse(null);
		if(user == null) throw new ApplicationRequestException("User Not Found");
		
		if(transaction.getTransactionDate() == null)
			transaction.setTransactionDate(new Date());
		transaction.setUser(user);
		
		//TODO validate limits and send email
		
		userTransactionRepository.save(transaction);
	}
	
	public void createUser (UserEntity user) {
		userRepository.save(user);
	}
	
	public void createBudget(Long userId, UserBudget budget) {
		UserEntity user = userRepository.findById(userId).orElse(null);
		if(user == null) throw new ApplicationRequestException("User Not Found");
		
		budget.setUser(user);
		try {
			userBudgetRepository.save(budget);
		}catch(Exception e) {
			if(e.getCause() instanceof ConstraintViolationException) throw new ApplicationRequestException(e.getMessage());
		}
		
	}
	
	
	
}
