package com.springboot.budgetapp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="user_transaction")
public class UserTransactionEntity {

	@Id
	@GeneratedValue
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss z", timezone = "EDT")
	private Date transactionDate;
	@ManyToOne
	@JoinColumn(name ="user_id" , foreignKey = @ForeignKey(name = "user_id_fk"))
	private UserEntity user;
	private Integer amount;
}
