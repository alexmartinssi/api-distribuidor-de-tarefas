package com.br.apipadrao.domain;

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

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Component
@Table(name = "api_task") //
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Task implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long              id;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Column(nullable = false, length = 100)
	private String            name;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Column(nullable = true, length = 100)
	private String            description;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User              user;
	@ManyToOne
	@JoinColumn(name = "register_id")
	private Register          register;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(register.getName());
		builder.append("\n");
		builder.append("Data Inicial: " + register.getInitialDate());
		builder.append("\n");
		builder.append("Data final: " + register.getFinalDate());
		builder.append("\n");
		builder.append("Premiação: " + register.getReward());
		builder.append("\n");
		builder.append("Tarefa: " + name);
		builder.append("\n");
		if (!description.isEmpty()) {
			builder.append("Descrição: " + description);
		}
		return builder.toString();
	}
}
