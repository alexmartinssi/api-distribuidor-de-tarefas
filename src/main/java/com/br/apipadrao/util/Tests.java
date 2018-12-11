package com.br.apipadrao.util;

import java.util.Random;

public class Tests {

	public static void main(String[] args) {

//		String password = "123";
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String hashedPassword = passwordEncoder.encode(password);
//
//		System.out.println(hashedPassword);

		Random random = new Random();

		int option = random.nextInt(3);

		System.out.println(option);

	}
}
