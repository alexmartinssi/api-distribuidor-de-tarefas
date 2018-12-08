package com.br.apipadrao.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender emailSender;
	@Autowired
	private JavaMailSender javaMailSender;

	private static final Logger log = LoggerFactory.getLogger(SmtpEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage email) {
		log.info("Envio de email.");
		emailSender.send(email);
		log.info("Email enviado.");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		log.info("Envio de email.");
		javaMailSender.send(msg);
		log.info("Email enviado.");

	}
}
