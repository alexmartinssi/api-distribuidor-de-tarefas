package com.br.apipadrao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    
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
  
}