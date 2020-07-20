package com.springboot.budgetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.budgetapp.entity.UserBudget;

public interface UserBudgetRepository extends JpaRepository<UserBudget,Long>{

}
