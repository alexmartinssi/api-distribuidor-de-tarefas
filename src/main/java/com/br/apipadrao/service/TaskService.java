package com.br.apipadrao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.apipadrao.dto.TaskDTO;
import com.br.apipadrao.entity.Task;
import com.br.apipadrao.repository.TaskRepository;

@Service
public class TaskService{
	
	@Autowired
    private TaskRepository taskRepository;
	
	public List<Task> list(){
		return taskRepository.findAll();
	}
	
	public Task findById(long id) {
		return taskRepository.getOne(id);
	}
	
	public Task create(TaskDTO taskDTO) {
        return taskRepository.save(taskDTO.getTask());
    }
	
	public Task update(TaskDTO taskDTO) throws Exception {
        return taskRepository.save(taskDTO.getTask());
    }
	
	public void remove(Long id) throws Exception{
		taskRepository.deleteById(id);
	}
}
