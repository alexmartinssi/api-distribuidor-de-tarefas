package com.br.apipadrao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.br.apipadrao.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	@Transactional(readOnly = true)
	User findByEmail(String email);
}
