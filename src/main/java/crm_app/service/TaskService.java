package crm_app.service;

import java.util.List;

import crm_app.repository.TaskRepository;
import entity.Task;

public class TaskService {
	
	private TaskRepository taskRepository = new TaskRepository();
	
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}
}
