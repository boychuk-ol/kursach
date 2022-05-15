package com.example.demo.Task;

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

    public void addTask(Task task)
    {
        repository.saveAndFlush(task);
    }

    public long numberOfTasks() {return repository.count();}

    public void deleteTask(long id){
        repository.deleteById(id);
    }



}