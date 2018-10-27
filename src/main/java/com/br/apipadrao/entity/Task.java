package com.br.apipadrao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Task {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String description;
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
