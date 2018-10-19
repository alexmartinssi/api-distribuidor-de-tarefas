package com.br.apipadrao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.apipadrao.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	User findOneByLogin(String login);
}
