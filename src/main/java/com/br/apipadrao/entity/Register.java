package com.br.apipadrao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Register {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotEmpty
	private User user;
	@NotEmpty
	private Task task;
	@NotEmpty
	private String period;
	@NotEmpty
	private String status;
	@NotEmpty
	private String reward;

}
