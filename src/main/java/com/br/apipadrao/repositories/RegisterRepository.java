package com.br.apipadrao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.apipadrao.domain.Register;

public interface RegisterRepository extends JpaRepository<Register,Long> {
}
