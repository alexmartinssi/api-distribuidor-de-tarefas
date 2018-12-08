package com.br.apipadrao.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.br.apipadrao.domain.Task;
import com.br.apipadrao.domain.User;

import lombok.Data;

@Data
public class RegisterDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String name;
	@NotNull(message = "Preenchimento obrigatório")
	private Date initialDate;
	@NotNull(message = "Preenchimento obrigatório")
	private Date finalDate;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String reward;
	@NotEmpty(message = "Preenchimento obrigatório")
	private List<User> users;
	@NotEmpty(message = "Preenchimento obrigatório")
	private List<Task> tasks;

}
