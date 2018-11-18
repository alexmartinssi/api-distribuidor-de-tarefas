package com.br.apipadrao.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.br.apipadrao.domain.User;

@Service
public interface EmailService {

	void sandEmail(SimpleMailMessage email);
	
	void sandNewPasswordEmail(User user, String newPass);
}
