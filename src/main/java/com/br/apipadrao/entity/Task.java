package com.br.apipadrao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Component
@Table(name = "api_task") //
@NoArgsConstructor
@Getter
@Setter
public class Task implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Column(nullable = false, length = 100)
	private String name;
	@NotEmpty(message="Preenchimento obrigatório")
	@Column(nullable = true, length = 100)
	private String description;
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	@ManyToOne
    @JoinColumn(name = "register_id")
    private Register register;

}
