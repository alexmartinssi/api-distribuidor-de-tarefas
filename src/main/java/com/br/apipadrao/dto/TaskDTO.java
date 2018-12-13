package com.br.apipadrao.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import com.br.apipadrao.domain.Register;
import com.br.apipadrao.domain.User;

import lombok.Data;

@Data
public class TaskDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Column(nullable = false, length = 100)
	private String name;
	@Column(nullable = true, length = 100)
	private String description;
	@Column(nullable = true, length = 20)
	private String status;
	private User user;
	private Register register;
}
