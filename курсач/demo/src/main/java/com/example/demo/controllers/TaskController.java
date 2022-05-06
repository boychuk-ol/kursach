package com.example.demo.controllers;

import com.example.demo.models.Task;
import com.example.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class TaskController {

    private TaskService service;

    @Autowired
    public TaskController(TaskService service)
    {
        this.service = service;
    }

    @GetMapping("/tasks")
    public String tasks(Model model)
    {
        List<Task> list = service.getAllTasks();
        model.addAttribute("title", "My tasks");
        model.addAttribute("all_tasks", list);
        return "allTasks";
    }

    @PostMapping("/tasks")
    public String addTask(@RequestParam String title,Model model)
    {
        System.out.println(service.numberOfTasks()+1);
        Task task = new Task(service.numberOfTasks()+1,title,"default");
        model.addAttribute("add_task", task);
        service.addTask(task);
        return "redirect:/tasks";
    }

}
