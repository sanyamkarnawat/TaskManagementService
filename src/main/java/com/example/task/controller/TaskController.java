package com.example.task.controller;

//TaskController.java

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.task.model.Task;
import com.example.task.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
//
//	@GetMapping("/getalltasks")
//	public List<Task> getAllTasks() {
//		return TaskService.getAllTasks();
//	}

	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
		Task task = taskService.getTaskById(taskId);
		return ResponseEntity.ok(task);
	}

	@PostMapping("/createtask")
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		Task savedTask = taskService.saveTask(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
	}

	@PatchMapping("/{taskId}/assign/{userId}")
	public ResponseEntity<Task> assignTaskToUser(@PathVariable Long taskId, @PathVariable Long userId) {
		Task assignedTask = taskService.assignTaskToUser(taskId, userId);
		return ResponseEntity.ok(assignedTask);
	}

	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
		taskService.deleteTask(taskId);
		return ResponseEntity.noContent().build();
	}

	// Additional endpoints for task prioritization, due date filtering, etc.

}
