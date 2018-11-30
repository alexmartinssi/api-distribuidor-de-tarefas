package com.br.apipadrao.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import com.br.apipadrao.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Table(name = "api_user", //
        uniqueConstraints = { //
                @UniqueConstraint(columnNames = { "username" }), //
                @UniqueConstraint(columnNames = { "email" }) //
		}) //
@Getter
@Setter
@EqualsAndHashCode
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long         id;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Column(nullable = false, length = 100)
	private String       name;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Column(nullable = false, length = 50, unique = true)
	private String       email;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Column(unique = true, nullable = false, length = 50)
	private String       username;
	@JsonIgnore
	@NotEmpty(message = "Preenchimento obrigatório")
	@Column(nullable = false, length = 200)
	private String       password;
	@Column(nullable = false, length = 10)
	private boolean      active;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "profiles")
	private Set<Integer> profiles = new HashSet<>();
	
	public User() {
		addProfile(Profile.USUARIO);
	}
	
	public User(Long id, @NotEmpty(message = "Preenchimento obrigatório") String name,
	        @NotEmpty(message = "Preenchimento obrigatório") String email,
	        @NotEmpty(message = "Preenchimento obrigatório") String username,
	        @NotEmpty(message = "Preenchimento obrigatório") String password, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.active = active;
		addProfile(Profile.USUARIO);
	}
	
	public User(@NotEmpty(message = "Preenchimento obrigatório") String name,
	        @NotEmpty(message = "Preenchimento obrigatório") String email,
	        @NotEmpty(message = "Preenchimento obrigatório") String username) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.active = true;
		addProfile(Profile.USUARIO);
	}
	
	public Set<Profile> getProfiles() {
		// Retorna os perfis dos clientes fazendo a conversão com o
		// Perfil.toEnum e transforma tudo em uma coleção
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addProfile(Profile profile) {
		profiles.add(profile.getCod());
	}
}
