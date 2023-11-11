package com.example.task.service;

//TaskService.java

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.task.exception.TaskNotFoundException;
import com.example.task.exception.UserNotFoundException;
import com.example.task.model.Task;
import com.example.task.model.User;
import com.example.task.repo.TaskRepository;
import com.example.task.repo.UserRepository;

import java.util.List;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
	private final UserRepository userRepository;

	public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
	}

	@Cacheable("tasks")
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	@CacheEvict(value = "tasks", allEntries = true)
	public Task saveTask(Task task) {
		return taskRepository.save(task);
	}

	public Task getTaskById(Long taskId) {
		return taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
	}

	public Task assignTaskToUser(Long taskId, Long userId) {
		Task task = getTaskById(taskId);
		User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
		task.setAssignedUser(user);
		return taskRepository.save(task);
	}

	public void deleteTask(Long taskId) {
		taskRepository.deleteById(taskId);
	}

	// Additional methods for task prioritization, due date filtering, etc.

}
