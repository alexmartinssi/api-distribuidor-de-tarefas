package com.br.apipadrao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.apipadrao.domain.Register;
import com.br.apipadrao.dto.RegisterDTO;
import com.br.apipadrao.repositories.RegisterRepository;

@Service
public class RegisterService {

	@Autowired
	private RegisterRepository registerRepository;

	public List<Register> list() {
		return registerRepository.findAll();
	}

	public Register findById(long id) {
		return registerRepository.getOne(id);
	}

	@Transactional
	public Register save(RegisterDTO registerDTO) {
		Register register = new Register(registerDTO.getName(), registerDTO.getInitialDate(),
				registerDTO.getFinalDate(), registerDTO.getReward());
		return registerRepository.save(register);
	}

	public void remove(Long id) throws Exception {
		registerRepository.deleteById(id);
	}

}
