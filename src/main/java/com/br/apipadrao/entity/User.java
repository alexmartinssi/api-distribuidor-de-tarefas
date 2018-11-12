package com.br.apipadrao.entity;



import java.io.Serializable;
import java.util.Collection;
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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.br.apipadrao.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Table(name = "api_user", //
	   uniqueConstraints = { //
		@UniqueConstraint(columnNames = {"username"}), //
		@UniqueConstraint(columnNames = {"email"}) //
	  }) //
@Getter
@Setter
@EqualsAndHashCode
public class User  implements Serializable, UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Column(nullable = false, length = 100)
	private String name;
	@NotEmpty(message="Preenchimento obrigatório")
	@Column(nullable = false, length = 50, unique = true)
	private String email;
	@NotEmpty(message="Preenchimento obrigatório")
	@Column(unique = true, nullable = false, length = 50)
	private String username;
	@JsonIgnore
	@NotEmpty(message="Preenchimento obrigatório")
	@Column(nullable = false, length = 50)
	private String password;
	@Column(nullable = false, length = 10)
	private boolean active;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="perfis")
	private Set<Integer> perfis = new HashSet<>();
	
	public User() {
		addPerfil(Perfil.USUARIO);
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
		addPerfil(Perfil.USUARIO);
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}
	@Override
	public boolean isEnabled() {
		return active;
	}
	public Set<Perfil> getPerfis() {
		//Retorna os perfis dos clientes fazendo a conversão com o Perfil.toEnum e transforma tudo em uma coleção
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
}
