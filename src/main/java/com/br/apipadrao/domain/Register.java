package com.br.apipadrao.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Component
@Table(name = "api_register")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Register implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Column(nullable = false, length = 100)
	private String name;
	@NotNull(message = "Preenchimento obrigatório")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date initialDate;
	@NotNull(message = "Preenchimento obrigatório")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date finalDate;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Column(nullable = false, length = 100)
	private String reward;

	public Register(String name, Date initialDate, Date finalDate, String reward) {
		this.name = name;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.reward = reward;
	}

}
