package com.br.apipadrao.entity;



import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Component
@Table(name = "api_user", //
	   uniqueConstraints = { //
		@UniqueConstraint(columnNames = {"username"}), //
		@UniqueConstraint(columnNames = {"email"}) //
	  }) //
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
}
