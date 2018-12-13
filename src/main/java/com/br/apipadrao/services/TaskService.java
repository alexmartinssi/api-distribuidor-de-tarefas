package com.br.apipadrao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.apipadrao.domain.Task;
import com.br.apipadrao.domain.User;
import com.br.apipadrao.dto.TaskDTO;
import com.br.apipadrao.repositories.TaskRepository;
import com.br.apipadrao.security.UserSS;
import com.br.apipadrao.services.exceptions.AuthorizationException;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserService userService;

	public List<Task> list() {
		return taskRepository.findAll();
	}

	public Page<Task> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS userSS = userService.authenticated();
		if (userSS == null) {
			throw new AuthorizationException("Usuário não autenticado");
		}

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		User user = userService.find(userSS.getId());

		return taskRepository.findByUser(user, pageRequest);
	}

	public Task findById(long id) {
		return taskRepository.getOne(id);
	}

	public Task save(TaskDTO taskDTO) {
		Task task = new Task(null, taskDTO.getName(), taskDTO.getDescription(), taskDTO.getStatus(), taskDTO.getUser(),
				taskDTO.getRegister());
		return taskRepository.save(task);
	}

	public void remove(Long id) throws Exception {
		taskRepository.deleteById(id);
	}
}
