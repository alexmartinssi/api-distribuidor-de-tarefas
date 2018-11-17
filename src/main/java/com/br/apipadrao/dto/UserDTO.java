package com.br.apipadrao.dto;

import java.io.Serializable;

import com.br.apipadrao.domain.User;

import lombok.Data;

@Data
public class UserDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String password;
}
