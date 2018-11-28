package com.br.apipadrao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.apipadrao.services.EmailService;
import com.br.apipadrao.services.SmtpEmailService;

@Configuration
public class Config {

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}