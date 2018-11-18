package com.br.apipadrao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.apipadrao.services.EmailService;
import com.br.apipadrao.services.SmtpEmailService;

@Configuration
public class Config {

    
//    @Autowired
//    private DBService dbService;
//    
//    @Value("${spring.jpa.hibernate.ddl-auto}")
//    private String strategy;
//    
//    @Bean
//    public boolean instantiateDatabase() throws ParseException {
//    	
//    	if(!"create".equals(strategy)) {
//    		return false;
//        }
//    	
//    	dbService.instantiateTestDatabase();
//        return true;
//    }
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
  
}