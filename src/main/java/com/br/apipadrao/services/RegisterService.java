package com.br.apipadrao.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.apipadrao.domain.Register;
import com.br.apipadrao.domain.Task;
import com.br.apipadrao.domain.User;
import com.br.apipadrao.dto.RegisterDTO;
import com.br.apipadrao.repositories.RegisterRepository;
import com.br.apipadrao.repositories.TaskRepository;
import com.br.apipadrao.repositories.UserRepository;

@Service
public class RegisterService {

	@Autowired
	private RegisterRepository registerRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TaskRepository taskRepository;

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

		register = registerRepository.save(register);

		List<User> users = registerDTO.getUsers();
		Random r = new Random();

		for (int i = 0; i < users.size(); i++) {
			User u = userRepository.findByEmail(users.get(i).getEmail());

			int sorteio = 0;
			Task t = null;
			while (true) {
				try {
					sorteio = r.nextInt(registerDTO.getTasks().size());
					t = registerDTO.getTasks().get(sorteio);

					if (t != null) {
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			t.setUser(u);
			t.setRegister(register);

			taskRepository.save(t);
			registerDTO.getTasks().remove(sorteio);
		}

		return register;
	}

	public void remove(Long id) throws Exception {
		registerRepository.deleteById(id);
	}

}
