package com.br.apipadrao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.apipadrao.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);
}
