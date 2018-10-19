package com.br.apipadrao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.apipadrao.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
