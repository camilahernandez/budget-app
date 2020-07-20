package com.springboot.budgetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.budgetapp.entity.UserTransactionEntity;

@Repository(value = "JpaRepository")
public interface UserTransactionRepository extends  JpaRepository<UserTransactionEntity,Long>{

	 List<UserTransactionEntity> findByUser(Long userId);
	
}

	
