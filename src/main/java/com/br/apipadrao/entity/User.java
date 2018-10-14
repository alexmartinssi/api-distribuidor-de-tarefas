package com.br.apipadrao.entity;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.scheduling.config.Task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String email;
	@NotEmpty
	private String login;
	@NotEmpty
	private String password;
	@OneToMany(targetEntity=Task.class, fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private List<Task> tasks;
}
