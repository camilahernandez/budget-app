package com.springboot.budgetapp.mail;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.springboot.budgetapp.config.EmailTemplateConfig;

@Service
public class EmailService implements IEmailService{

	private final JavaMailSender javaMailSender;
	private final EmailTemplateConfig emailConfig;
	private final SimpleMailMessage simpleMessageTemplate; 
	
	@Autowired
	public EmailService(JavaMailSender javaMailSender, EmailTemplateConfig emailConfig,SimpleMailMessage simpleMessageTemplate) {
		this.javaMailSender = javaMailSender;
		this.emailConfig = emailConfig;
		this.simpleMessageTemplate = simpleMessageTemplate;
	}
	@Override
	public void sendSimpleMessage(String to, String subject, Object[] args) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailConfig.getFrom());
		message.setTo(to);
		message.setSubject(emailConfig.getFromName() + ":"+ subject);
		String text = simpleMessageTemplate.getText();
		for(Object o : args) {
			text = text.replaceFirst("(arg)", String.valueOf(o));
		}
		message.setText(text);
		
		javaMailSender.send(message);
	}

	@Override
	public void sendMessageUsingTemplate(String to, String subject, Map<String, Object> templateModel) {
		// TODO Auto-generated method stub
		
	}

}
