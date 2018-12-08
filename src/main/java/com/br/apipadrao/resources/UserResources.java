package com.br.apipadrao.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.apipadrao.domain.User;
import com.br.apipadrao.dto.UserDTO;
import com.br.apipadrao.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserResources {

	@Autowired
	private UserService userService;

	@PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
	@GetMapping("/v1/list/")
	public ResponseEntity<List<User>> list() {
		List<User> users = userService.list();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@GetMapping("/v1/user/{id}")
	public ResponseEntity<?> find(@PathVariable("id") long id) {
		User user = userService.find(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<User> find(@RequestParam(value = "value") String email) {
		User obj = userService.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping("/v1/create/")
	public ResponseEntity<?> create(@Valid @RequestBody UserDTO userDTO) {
		User user = userService.create(userDTO);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@PutMapping("/v1/update/")
	public ResponseEntity<?> update(@Valid @RequestBody UserDTO userDTO) {
		try {
			User user = userService.update(userDTO);
			if (user == null) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
	}

	@PreAuthorize("hasAnyRole('ADMIN', USUARIO)")
	@DeleteMapping("/v1/remove/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		try {
			User user = userService.remove(id);
			if (user == null) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
	}
}