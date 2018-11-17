package com.br.apipadrao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.apipadrao.domain.Register;
import com.br.apipadrao.dto.RegisterDTO;
import com.br.apipadrao.repositories.RegisterRepository;

@Service
public class RegisterService{
	
	@Autowired
    private RegisterRepository registerRepository;
	
	public List<Register> list(){
		return registerRepository.findAll();
	}
	
	public Register findById(long id) {
		return registerRepository.getOne(id);
	}
	
	public Register create(RegisterDTO registerDTO) {
        return registerRepository.save(registerDTO.getRegister());
    }
	
	public Register update(RegisterDTO registerDTO) throws Exception {
        return registerRepository.save(registerDTO.getRegister());
    }
	
	public void remove(Long id) throws Exception{
		registerRepository.deleteById(id);
	}
	
	
}
