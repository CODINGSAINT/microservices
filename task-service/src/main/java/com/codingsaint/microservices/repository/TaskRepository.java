package com.codingsaint.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingsaint.microservices.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	List<Task>findByUserId(Long userId);
}
