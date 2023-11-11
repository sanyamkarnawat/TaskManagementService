package com.example.task.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task.model.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {

}
