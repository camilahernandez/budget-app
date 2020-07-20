package com.springboot.budgetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.budgetapp.entity.IntervalType;
import com.springboot.budgetapp.entity.UserBudget;
import com.springboot.budgetapp.entity.UserEntity;

public interface UserBudgetRepository extends JpaRepository<UserBudget,Long>{

	UserBudget findByUserAndInterval(UserEntity user, IntervalType interval);
}
