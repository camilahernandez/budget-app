package com.springboot.budgetapp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.budgetapp.entity.IntervalType;
import com.springboot.budgetapp.entity.UserBudget;
import com.springboot.budgetapp.entity.UserEntity;
import com.springboot.budgetapp.entity.UserTransactionEntity;
import com.springboot.budgetapp.exception.ApplicationRequestException;
import com.springboot.budgetapp.mail.IEmailService;
import com.springboot.budgetapp.repository.UserBudgetRepository;
import com.springboot.budgetapp.repository.UserRepository;
import com.springboot.budgetapp.repository.UserTransactionRepository;
@Service
public class UserTransactionServices {
	
	UserTransactionRepository userTransactionRepository;
	UserRepository userRepository;
	UserBudgetRepository userBudgetRepository;
	IEmailService emailService;
	
	@Autowired
	public UserTransactionServices(UserTransactionRepository userTransactionRepository,UserRepository userRepository
			,UserBudgetRepository userBudgetRepository,IEmailService emailService) {
		this.userTransactionRepository = userTransactionRepository;
		this.userRepository = userRepository;
		this.userBudgetRepository = userBudgetRepository;
		this.emailService = emailService;
	}
	
	public List<UserTransactionEntity> getTransactionsForUser(Long userId){
		Optional<UserEntity> user = userRepository.findById(userId);
		if(!user.isPresent()) throw new ApplicationRequestException("User Not Found");
		return userTransactionRepository.findByUser(user.get());
	}
	
	public void createTransaction(Long userId, UserTransactionEntity transaction) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
		UserEntity user = userRepository.findById(userId).orElse(null);
		if(user == null) throw new ApplicationRequestException("User Not Found");
		
		if(transaction.getTransactionDate() == null)
			transaction.setTransactionDate(new Date());
		transaction.setUser(user);
		userTransactionRepository.save(transaction);
		
		List<UserTransactionEntity> sameDateTransactions = userTransactionRepository.findByTransactionDate(transaction.getTransactionDate());
		UserBudget dailyBudget = userBudgetRepository.findByUserAndInterval(user, IntervalType.DAILY);
		Long sum = 0L;
		if(sameDateTransactions != null) {
			 sum = sameDateTransactions
					.stream()
					.mapToLong(UserTransactionEntity::getAmount)
					.sum();
		}
		
		if(sum >= dailyBudget.getMaxLimit()) {
			emailService.sendSimpleMessage(user.getEmail(), " Overstepped Daily Limits" , 
				new Object[] {transaction.getId(),format.format(transaction.getTransactionDate()), sum - dailyBudget.getMaxLimit()}		);
		}
			
		
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
