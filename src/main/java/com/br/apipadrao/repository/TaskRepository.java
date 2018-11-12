package com.br.apipadrao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.apipadrao.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {
	List<Task> findAllByUserId(Long id);
}
