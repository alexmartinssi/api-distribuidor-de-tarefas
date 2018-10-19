package com.br.apipadrao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.apipadrao.entity.Register;

public interface RegisterRepository extends JpaRepository<Register,Long> {
}
