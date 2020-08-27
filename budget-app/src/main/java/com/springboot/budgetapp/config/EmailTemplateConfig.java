package com.springboot.budgetapp.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "app.email.template")
public class EmailTemplateConfig {

	@Getter @Setter private String from;
	@Getter @Setter private String fromName;
	
	@Bean
	public SimpleMailMessage templateSimpleMessage() {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText("Transaction ID: (arg) \n Date: (arg) \n Overflow Amount: (arg) ");
		
		return message;
	}
	
}
