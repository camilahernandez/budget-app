package com.springboot.budgetapp.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTransactionDto {

	private Date transactionDate;
	private Integer amount;
}
