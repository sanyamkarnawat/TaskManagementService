package com.example.task.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
