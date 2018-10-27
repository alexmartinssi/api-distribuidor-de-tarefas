package com.br.apipadrao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apipadrao.dto.RegisterDTO;
import com.br.apipadrao.entity.Register;
import com.br.apipadrao.service.RegisterService;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private RegisterService registerService;
	
	@PostMapping("/v1/register/create")
	public BodyBuilder create(@RequestBody RegisterDTO registerDTO) {
		Register checkRegister = registerService.create(registerDTO);
		if(checkRegister == null) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED);
	}
	
	@PutMapping("/v1/register/update")
	public BodyBuilder update(@RequestBody RegisterDTO registerDTO) {
		Register checkRegister;
		try {
			checkRegister = registerService.update(registerDTO);
			if(checkRegister == null) {
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST);
			}
			return ResponseEntity.status(HttpStatus.OK);	
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/v1/register/{id}")
	public BodyBuilder remove(@PathVariable Long id) {
		try {
			registerService.remove(id);
			return ResponseEntity.status(HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
	}
}
