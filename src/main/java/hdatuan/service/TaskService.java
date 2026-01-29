package hdatuan.service;

import java.util.List;

import hdatuan.repository.TaskRepository;
import hdatuan.entity.Task;

public class TaskService {
	
	private TaskRepository taskRepository = new TaskRepository();
	
	public boolean updateTask(Task task) {
		return taskRepository.updateTask(task);
	}
	
	public Task getTask(int taskId) {
		return taskRepository.findTask(taskId);
	}
	
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}
	
	public List<Task> getTaskById(int userId){
		return taskRepository.findByUserId(userId);
	}
	
	public List<Task> getTaskByUserAndJob(int userId, int jobId){
		return taskRepository.findByUserAndJob(userId, jobId);
	}
	
}

