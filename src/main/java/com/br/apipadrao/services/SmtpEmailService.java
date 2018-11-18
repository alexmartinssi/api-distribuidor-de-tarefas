package com.br.apipadrao.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {
	
	@Autowired
	private MailSender emailSender;
	
	private static final Logger log = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sandEmail(SimpleMailMessage email) {
		log.info("Envio de email.");
		emailSender.send(email);
		log.info("Email enviado.");
	}
	
	

}
