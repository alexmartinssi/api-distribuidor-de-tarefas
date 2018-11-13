package com.br.apipadrao.dto;

import java.io.Serializable;

import com.br.apipadrao.entity.Register;

import lombok.Data;

@Data
public class RegisterDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Register register;
}
