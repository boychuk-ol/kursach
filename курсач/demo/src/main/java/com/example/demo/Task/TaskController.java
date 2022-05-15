package com.example.demo.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/tasks/add")
    public String addTask(@RequestParam String title,Model model)
    {
        System.out.println("number of tasks before add = " + service.numberOfTasks());
        Task task = new Task(title,"default");
        model.addAttribute("added_task", task);
        service.addTask(task);
        System.out.println("number of tasks after add = " + service.numberOfTasks());
        return "redirect:/tasks";
    }

    @RequestMapping(value="/tasks/remove/{id}", method = RequestMethod.DELETE)
    public String removeTask(@PathVariable(value="id") long taskId)
    {
        service.deleteTask(taskId);
        System.out.println("number of tasks after delete = " + service.numberOfTasks());
        return "redirect:/tasks";
    }



}
