package com.br.apipadrao.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.br.apipadrao.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userDetailsService;
	
	public static final String[] PUBLIC_MATCHERS_GET = {"/tasks/**", "/users/**", "/registers/**"};
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors().and().csrf().disable(); //
        http.authorizeRequests() //
		.antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**", "/webfonts/**").permitAll() //
		.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll() //
		.anyRequest().authenticated(); //
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//		return (CorsConfigurationSource) source;
//	}
    
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
