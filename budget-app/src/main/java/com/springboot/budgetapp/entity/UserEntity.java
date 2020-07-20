package com.springboot.budgetapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity(name="user_table")
public class UserEntity {

	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	
	public UserEntity() {
		
	}
	
	
}
