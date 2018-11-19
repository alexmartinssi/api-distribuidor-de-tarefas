package com.br.apipadrao.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.apipadrao.domain.User;
import com.br.apipadrao.repositories.UserRepository;
import com.br.apipadrao.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEnconder;
	
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();
	
	public void sandNewPassword(String email) {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		user.setPassword(passwordEnconder.encode(newPass));
		
		userRepository.save(user);
		emailService.sendNewPasswordEmail(user, newPass);
	}

	//Cria uma senha
	private String newPassword() {
		char[] vet = new char[10];
		for(int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opc = random.nextInt(3);
		if(opc == 0) {//gera número
			return (char) (random.nextInt(10) + 48);
		}else if(opc == 1) { //gera letra Maiúscula
			return (char) (random.nextInt(26) + 65);
		} else { //gera letra minúscula
			return (char) (random.nextInt(26) + 72);
		}
	}
}
