package com.example.tasktracker.week9.repository;

import com.example.tasktracker.week9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}