package com.br.apipadrao.dto;

import com.br.apipadrao.entity.User;

import lombok.Data;

@Data
public class UserDTO {

	private User user;
	private String password;
}
