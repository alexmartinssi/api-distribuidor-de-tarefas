package com.br.apipadrao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.apipadrao.domain.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {
	List<Task> findAllByUserId(Long id);
}
