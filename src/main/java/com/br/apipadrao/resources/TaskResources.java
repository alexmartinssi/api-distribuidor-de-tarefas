package com.br.apipadrao.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.apipadrao.domain.Task;
import com.br.apipadrao.dto.TaskDTO;
import com.br.apipadrao.services.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskResources {

	@Autowired
	private TaskService taskService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/v1/list-all-tasks/")
	public ResponseEntity<List<Task>> list() {
		List<Task> tasks = taskService.list();
		if (tasks.isEmpty()) {
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	@GetMapping("/v1/list/")
	public ResponseEntity<Page<Task>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "register.initialDate") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Task> objs = taskService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(objs);
	}

	@GetMapping("/v1/task/{id}")
	public ResponseEntity<?> get(@PathVariable("id") long id) {
		Task task = taskService.findById(id);
		if (task == null) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/v1/create/")
	public ResponseEntity<?> create(@Valid @RequestBody TaskDTO taskDTO) {
		Task task = taskService.save(taskDTO);
		if (task == null) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(task, HttpStatus.CREATED);
	}

	@PutMapping("/v1/change-status/")
	public ResponseEntity<?> changeStatus(@Valid @RequestBody TaskDTO taskDTO) {
		try {
			Task task = taskService.changeStatus(taskDTO);
			if (task == null) {
				return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Task>(task, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Task>(HttpStatus.CONFLICT);
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/v1/update/")
	public ResponseEntity<?> update(@Valid @RequestBody TaskDTO taskDTO) {
		try {
			Task task = taskService.save(taskDTO);
			if (task == null) {
				return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Task>(task, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Task>(HttpStatus.CONFLICT);
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
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
