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
		int indexUserTask = 0;
		int indexUser = 0;
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
			int option = random.nextInt(2);
			if (option == 0) {
				for (indexUser = 0; indexUser < users.size(); indexUser++) {
					User user = userRepository.findByEmail(users.get(indexUser).getEmail());
					while (true) {
						try {
							if (tasks.isEmpty() && tasks.size() == 0) {
								break;
							} else {
								indexUserTask = random.nextInt(tasks.size());
								task = tasks.get(indexUserTask);
								if (task != null) {
									break;
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (task != null) {
						task.setUser(user);
						task.setRegister(register);
						if (task.getDescription().isEmpty()) {
							task.setDescription("Sem descrição");
						}
						taskRepository.save(task);
						task = null;
						tasks.remove(indexUserTask);
					}
				}
			} else if (option == 1) {
				indexUser = random.nextInt(users.size());
				User user = userRepository.findByEmail(users.get(indexUser).getEmail());
				while (true) {
					try {
						indexUserTask = random.nextInt(tasks.size());
						task = tasks.get(indexUserTask);
						if (task != null) {
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (task != null) {
					task.setUser(user);
					task.setRegister(register);
					if (task.getDescription().isEmpty()) {
						task.setDescription("Sem descrição");
					}
					taskRepository.save(task);
					task = null;
					tasks.remove(indexUserTask);
				}
			}
			indexUser = 0;
		}
		return register;
	}

	public void remove(Long id) throws Exception {
		registerRepository.deleteById(id);
	}

}
