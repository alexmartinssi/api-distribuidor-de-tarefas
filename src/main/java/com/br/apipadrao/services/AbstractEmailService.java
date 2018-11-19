package com.br.apipadrao.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.br.apipadrao.domain.Task;
import com.br.apipadrao.domain.User;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String emailOrigem;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Override
	public void sendNewPasswordEmail(User user, String newPass) {
		SimpleMailMessage simples = prepareNewPasswordEmail(user, newPass);
		sendEmail(simples);
	}

	protected SimpleMailMessage prepareNewPasswordEmail (User user, String newPass) {
		SimpleMailMessage simple = new SimpleMailMessage();
		simple.setTo(user.getEmail());
		simple.setFrom(emailOrigem);
		simple.setSubject("Nova senha");
		simple.setSentDate(new Date(System.currentTimeMillis()));
		simple.setText("Sua nova senha gerada de forma aleatória: "+newPass);
		
		return simple;
	}
	
	protected String htmlFromTemplateRegister(Task task) {
		Context context = new Context();
		context.setVariable("task", task);
		templateEngine.process("email/register", context);
		return null;
	}
	
}
