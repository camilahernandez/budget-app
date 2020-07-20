package com.springboot.budgetapp.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.ForeignKey;
import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "user_budget",
uniqueConstraints = @UniqueConstraint(
		columnNames = {"user_id",
				"interval"
				}
		)
)
public class UserBudget {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id" ,foreignKey = @ForeignKey(name="user_id_fk"))
	private UserEntity user;
	@Column(name = "max_limit")
	private Long maxLimit;
	@Enumerated(EnumType.STRING)
	private IntervalType interval;
	
	
}
