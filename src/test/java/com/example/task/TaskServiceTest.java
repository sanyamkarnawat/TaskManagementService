package com.example.task;

//TaskServiceTest.java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.task.model.Task;
import com.example.task.model.User;
import com.example.task.repo.TaskRepository;
import com.example.task.repo.UserRepository;
import com.example.task.service.TaskService;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

 @InjectMocks
 private TaskService taskService;

 @Mock
 private TaskRepository taskRepository;

 @Mock
 private UserRepository userRepository;

 @Test
 void getAllTasks() {
     // Mock the behavior of the repository
     when(taskRepository.findAll()).thenReturn(Collections.emptyList());

     // Call the service method
     var tasks = taskService.getAllTasks();

     // Verify the result
     assertNotNull(tasks);
     assertTrue(tasks.isEmpty());

     // Verify that the repository method was called
     verify(taskRepository, times(1)).findAll();
 }

 @Test
 void saveTask() {
     // Mock the behavior of the repository
     when(taskRepository.save(any(Task.class))).thenReturn(new Task());

     // Call the service method
     var savedTask = taskService.saveTask(new Task());

     // Verify the result
     assertNotNull(savedTask);

     // Verify that the repository method was called
     verify(taskRepository, times(1)).save(any(Task.class));
 }

 @Test
 void getTaskById() {
     // Mock the behavior of the repository
     when(taskRepository.findById(anyLong())).thenReturn(Optional.of(new Task()));

     // Call the service method
     var task = taskService.getTaskById(1L);

     // Verify the result
     assertNotNull(task);

     // Verify that the repository method was called
     verify(taskRepository, times(1)).findById(anyLong());
 }

 @Test
 void assignTaskToUser() {
     // Mock the behavior of the repository
     when(taskRepository.findById(anyLong())).thenReturn(Optional.of(new Task()));
     when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));

     // Call the service method
     var assignedTask = taskService.assignTaskToUser(1L, 1L);

     // Verify the result
     assertNotNull(assignedTask);

     // Verify that the repository methods were called
     verify(taskRepository, times(1)).findById(anyLong());
     verify(userRepository, times(1)).findById(anyLong());
     verify(taskRepository, times(1)).save(any(Task.class));
 }

 @Test
 void deleteTask() {
     // Call the service method
     taskService.deleteTask(1L);

     // Verify that the repository method was called
     verify(taskRepository, times(1)).deleteById(anyLong());
 }

 // Add more test methods based on your specific requirements

}
