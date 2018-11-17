package com.br.apipadrao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.br.apipadrao.domain.Task;
import com.br.apipadrao.domain.User;

public interface TaskRepository extends JpaRepository<Task,Long> {
	
	@Transactional(readOnly = true)
	Page<Task> findByUser(User user, Pageable pegable);
}
