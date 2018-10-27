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
	@OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="register_id")
    private List<User> users;
	@NotEmpty
	private String period;
	@NotEmpty
	private String reward;

}
