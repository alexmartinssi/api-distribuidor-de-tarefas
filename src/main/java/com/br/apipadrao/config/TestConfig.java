package com.br.apipadrao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.apipadrao.services.EmailService;
import com.br.apipadrao.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {


//    @Autowired
//    private DBService dbService;
//    
//    @Bean
//    public boolean instantiateDatabase() throws ParseException {
//        dbService.instantiateTestDatabase();
//        return true;
//    }

    @Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
    
}