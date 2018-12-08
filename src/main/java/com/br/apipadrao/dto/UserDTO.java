package com.br.apipadrao.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Column(nullable = false, length = 100)
	private String name;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Column(nullable = false, length = 50, unique = true)
	private String email;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Column(unique = true, nullable = false, length = 50)
	private String username;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Column(nullable = false, length = 200)
	private String password;
}
