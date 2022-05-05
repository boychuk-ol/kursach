package com.example.demo.services;

import com.example.demo.models.Task;
import com.example.demo.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository)
    {
        this.repository = repository;
    }

    public List<Task> getAllTasks()
    {
        return repository.findAll();
    }
}