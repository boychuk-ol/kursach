package com.example.demo.Task;

import com.example.demo.User.User;
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

    public List<Task> getAllOnlyImportantTasks(boolean isImportant){return repository.findAllByIsImportant(isImportant);}

    public List<Task> getAllOnlyDoneTasks(boolean isDone){return repository.findAllByIsDone(isDone);}

    public List<Task> getAllTasksByUser(User user) {return repository.findAllByUser(user);}

    public Task getTaskById(long id){
        return repository.getOne(id);}

    public void addTask(Task task)
    {
        repository.saveAndFlush(task);
    }

    public long numberOfTasks() {return repository.count();}

    public void deleteTask(long id){
        repository.deleteById(id);
    }



}