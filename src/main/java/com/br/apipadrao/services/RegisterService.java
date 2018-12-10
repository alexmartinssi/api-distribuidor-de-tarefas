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
		Random random = new Random();
		int index = 0;
		int userListIndex = 0;
		Task task = null;
		List<Task> tasks = registerDTO.getTasks();
		List<User> users = registerDTO.getUsers();
		Register register = new Register(registerDTO.getName(), registerDTO.getInitialDate(),
				registerDTO.getFinalDate(), registerDTO.getReward());
		register = registerRepository.save(register);
		while (true) {
			if (tasks.isEmpty()) {
				break;
			}
			for (userListIndex = 0; userListIndex < users.size(); userListIndex++) {
				User u = userRepository.findByEmail(users.get(userListIndex).getEmail());
				while (true) {
					try {
						if (tasks.isEmpty() && tasks.size() == 0) {
							break;
						} else {
							index = random.nextInt(tasks.size());
							task = tasks.get(index);
							if (task != null) {
								break;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (task != null) {
					task.setUser(u);
					task.setRegister(register);
					if (task.getDescription().isEmpty()) {
						task.setDescription("Sem descrição");
					}
					taskRepository.save(task);
					task = null;
					tasks.remove(index);
				}
			}
			userListIndex = 0;
		}
		return register;
	}

	public void remove(Long id) throws Exception {
		registerRepository.deleteById(id);
	}

}
