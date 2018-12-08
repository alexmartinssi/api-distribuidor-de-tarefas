package com.br.apipadrao.dto;

import java.io.Serializable;

import com.br.apipadrao.domain.Task;

import lombok.Data;

@Data
public class TaskDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Task task;
}
