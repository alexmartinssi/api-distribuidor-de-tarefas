package com.br.apipadrao.controller;

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

import com.br.apipadrao.dto.RegisterDTO;
import com.br.apipadrao.entity.Register;
import com.br.apipadrao.service.RegisterService;

@RestController
@RequestMapping("/api/registers")
public class RegisterController {

	@Autowired
	private RegisterService registerService;
	
	@GetMapping("/v1/list/")
	public ResponseEntity<List<Register>> list(){
		List<Register> registers = registerService.list();
		if(registers.isEmpty()) {
			return new ResponseEntity<List<Register>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Register>>(registers, HttpStatus.OK);
	}
	
	@GetMapping("/v1/register/{id}")
    public ResponseEntity<?> get(@PathVariable("id") long id) {
        Register register = registerService.findById(id);
        if (register == null) {
            return new ResponseEntity<Register>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Register>(register, HttpStatus.OK);
    }
	
	@PostMapping("/v1/create/")
	public ResponseEntity<?> create(@Valid @RequestBody RegisterDTO registerDTO) {
		Register register = registerService.create(registerDTO);
		if(register == null) {
			return new ResponseEntity<Register>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Register>(register, HttpStatus.CREATED);
	}
	
	@PutMapping("/v1/update/")
	public ResponseEntity<?> update(@Valid @RequestBody RegisterDTO registerDTO) {
		try {
			Register register = registerService.update(registerDTO);
			if(register == null) {
				return new ResponseEntity<Register>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Register>(register, HttpStatus.OK);	
		} catch (Exception e) {
			return new ResponseEntity<Register>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/v1/remove/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		try {
			registerService.remove(id);
			return new ResponseEntity<Register>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Register>(HttpStatus.CONFLICT);
		}
	}
}
