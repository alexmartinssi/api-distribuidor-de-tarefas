package com.br.apipadrao.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apipadrao.dto.TaskDTO;
import com.br.apipadrao.entity.Task;
import com.br.apipadrao.entity.User;
import com.br.apipadrao.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping("/v1/list/")
	public ResponseEntity<List<Task>> list(){
		List<Task> tasks = taskService.list();
		if(tasks.isEmpty()) {
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	@GetMapping("/v1/listByUserId/")
	public ResponseEntity<List<Task>> list(Principal principal){
		User user = (User) principal;
		List<Task> tasks = taskService.listByUserId(user.getId());
		if(tasks.isEmpty()) {
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	@GetMapping("/v1/task/{id}")
    public ResponseEntity<?> get(@PathVariable("id") long id) {
        Task task = taskService.findById(id);
        if (task == null) {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }
	
	@PostMapping("/v1/create/")
	public ResponseEntity<?> create(@Valid @RequestBody TaskDTO taskDTO) {
		Task task = taskService.create(taskDTO);
		if(task == null) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(task, HttpStatus.CREATED);
	}
	
	@PutMapping("/v1/update/")
	public ResponseEntity<?> update(@Valid @RequestBody TaskDTO taskDTO) {
		try {
			Task task= taskService.update(taskDTO);
			if(task == null) {
				return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Task>(task, HttpStatus.OK);	
		} catch (Exception e) {
			return new ResponseEntity<Task>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/v1/remove/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		try {
			taskService.remove(id);
			return new ResponseEntity<Task>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Task>(HttpStatus.CONFLICT);
		}
	}
}
