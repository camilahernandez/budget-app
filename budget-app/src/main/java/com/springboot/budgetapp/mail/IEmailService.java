package com.springboot.budgetapp.mail;
import java.util.Map;

public interface IEmailService {

	void sendSimpleMessage(String to,String subject, Object[] args);
	void sendMessageUsingTemplate(String to, String subject, Map<String,Object> templateModel);
}
