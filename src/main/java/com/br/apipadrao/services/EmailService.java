package com.br.apipadrao.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.br.apipadrao.domain.Task;
import com.br.apipadrao.domain.User;

@Service
public interface EmailService {

	void sendEmail(SimpleMailMessage email);
	
	void sendNewPasswordEmail(User user, String newPass);
	
	void sendRegisterHtmlEmail(Task task);
	
	void sendHtmlEmail(MimeMessage msg);
	
}
