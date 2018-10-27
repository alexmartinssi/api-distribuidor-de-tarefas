package com.br.apipadrao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apipadrao.dto.TaskDTO;
import com.br.apipadrao.entity.Task;
import com.br.apipadrao.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/v1/task/create")
	public BodyBuilder create(@RequestBody TaskDTO taskDTO) {
		Task checkTask = taskService.create(taskDTO);
		if(checkTask == null) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED);
	}
	
	@PutMapping("/v1/task/update")
	public BodyBuilder update(@RequestBody TaskDTO taskDTO) {
		Task checkTask;
		try {
			checkTask = taskService.update(taskDTO);
			if(checkTask == null) {
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST);
			}
			return ResponseEntity.status(HttpStatus.OK);	
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/v1/task/{id}")
	public BodyBuilder remove(@PathVariable Long id) {
		try {
			taskService.remove(id);
			return ResponseEntity.status(HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
	}
}
