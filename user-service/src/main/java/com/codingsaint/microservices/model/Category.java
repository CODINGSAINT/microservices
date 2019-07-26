package com.codingsaint.microservices.model;

import java.util.HashSet;
import java.util.Set;


public class Category {
	private Long categoryId;
	private String name;
	
	private Set<Task> tasks = new HashSet<>();

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

}
