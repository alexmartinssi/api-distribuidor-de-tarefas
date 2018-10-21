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
import org.springframework.web.bind.annotation.RestController;

import com.br.apipadrao.dto.UserDTO;
import com.br.apipadrao.entity.User;
import com.br.apipadrao.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/v1/user/create")
	public BodyBuilder create(@RequestBody UserDTO userDTO) {
		User checkUser = userService.create(userDTO);
		if(checkUser == null) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED);
	}
	
	@PutMapping("/v1/user/update")
	public BodyBuilder update(@RequestBody UserDTO userDTO) {
		User checkUser;
		try {
			checkUser = userService.update(userDTO);
			if(checkUser == null) {
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST);
			}
			return ResponseEntity.status(HttpStatus.OK);	
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/v1/user/{id}")
	public BodyBuilder remove(@PathVariable Long id) {
		User checkUser;
		try {
			checkUser = userService.remove(id);
			if(checkUser == null) {
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST);
			}
			return ResponseEntity.status(HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
	}
}