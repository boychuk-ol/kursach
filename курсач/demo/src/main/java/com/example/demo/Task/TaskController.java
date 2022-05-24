package com.example.demo.Task;

import com.example.demo.User.UserService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Predicate;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/","/tasks", "/home"},method = RequestMethod.GET)
    public String allTasks(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String s = authentication.getName();
        List<Task> list;

        if(userService.findByUsername(s)!=null) {
            list = taskService.getAllTasksByUser(userService.findByUsername(s));
        }
        else if(userService.findByUsername(s)==null)
        {
            list = taskService.getAllTasks();
            list.removeIf(user -> user.getUser()!=null);
        }
        else
        {
            throw new ObjectNotFoundException(null,"User");
        }

        model.addAttribute("title", "My tasks");
        model.addAttribute("all_tasks", list);
        model.addAttribute("username", s);

        return "testhome";
    }

    @PostMapping("/tasks/add")
    public String addTask(@RequestParam String title,Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String s = authentication.getName();

        Task task = new Task(title,"default",false, false);

        if (authentication.getName()!=null) {
            task = new Task(title, "default", false,false, userService.findByUsername(s));
        }

        model.addAttribute("added_task", task);
        taskService.addTask(task);
        return "redirect:/tasks";
    }


    @RequestMapping(value="/tasks/remove/{id}", method = RequestMethod.DELETE)
    public String removeTask(@PathVariable(value="id") long taskId)
    {
        taskService.deleteTask(taskId);
        return "redirect:/tasks";
    }

    @PostMapping(value = "/tasks/changeImportance/{id}")
    public String changeImportance(@PathVariable(value="id") long taskId, Model model)
    {
        Task task = taskService.getTaskById(taskId);

        task.setImportant(!taskService.getTaskById(taskId).isImportant());
        taskService.addTask(task);

        return "redirect:/tasks";
    }

    @PostMapping(value = "/tasks/changeDone/{id}")
    public String changeDone(@PathVariable(value="id") long taskId, Model model)
    {

        Task task = taskService.getTaskById(taskId);

        task.setDone(!taskService.getTaskById(taskId).isDone());
        taskService.addTask(task);

        return "redirect:/tasks";
    }

    @GetMapping(value = "/important")
    public String important(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String s = authentication.getName();
        List<Task> list = taskService.getAllOnlyImportantTasks(true);

        if (userService.findByUsername(s)!=null) {
            list.retainAll(taskService.getAllTasksByUser(userService.findByUsername(s)));
        }
        else if(userService.findByUsername(s)==null)
        {
            list.removeIf(user -> user.getUser()!=null);
        }
        else
        {
            throw new ObjectNotFoundException(null,"User");
        }

        model.addAttribute("title", "Important");
        model.addAttribute("all_tasks", list);
        model.addAttribute("username", s);

        return "testhome";
    }

    @GetMapping(value = "/done")
    public String done(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String s = authentication.getName();
        List<Task> list = taskService.getAllOnlyDoneTasks(true);

        if (userService.findByUsername(s)!=null) {
            list.retainAll(taskService.getAllTasksByUser(userService.findByUsername(s)));
        }
        else if(userService.findByUsername(s)==null)
        {
            list.removeIf(user -> user.getUser()!=null);
        }
        else
        {
            throw new ObjectNotFoundException(null,"User");
        }

        model.addAttribute("title", "Done");
        model.addAttribute("all_tasks", list);
        model.addAttribute("username", s);

        return "testhome";
    }

    // not done
    @GetMapping(value = "/todo")
    public String todo(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String s = authentication.getName();
        List<Task> list = taskService.getAllOnlyDoneTasks(false);

        if (userService.findByUsername(s)!=null) {
            list.retainAll(taskService.getAllTasksByUser(userService.findByUsername(s)));
        }
        else if(userService.findByUsername(s)==null)
        {
            list.removeIf(user -> user.getUser()!=null);
        }
        else
        {
            throw new ObjectNotFoundException(null,"User");
        }

        model.addAttribute("title", "To do");
        model.addAttribute("all_tasks", list);
        model.addAttribute("username", s);

        return "testhome";
    }


}
