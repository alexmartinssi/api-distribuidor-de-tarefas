package com.br.apipadrao.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.br.apipadrao.domain.User;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String emailOrigem;
	
	@Override
	public void sandNewPasswordEmail(User user, String newPass) {
		SimpleMailMessage simples = prepareNewPasswordEmail(user, newPass);
		sandEmail(simples);
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
	
}
