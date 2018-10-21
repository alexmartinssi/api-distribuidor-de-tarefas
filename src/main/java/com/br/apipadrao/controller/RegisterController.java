package com.br.apipadrao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apipadrao.repository.RegisterRepository;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private RegisterRepository registerRepository;
}
