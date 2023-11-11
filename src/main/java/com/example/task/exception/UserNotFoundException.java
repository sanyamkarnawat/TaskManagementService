package com.example.task.exception;

public class UserNotFoundException extends RuntimeException {

 public UserNotFoundException() {
     super("User not found");
 }
}
