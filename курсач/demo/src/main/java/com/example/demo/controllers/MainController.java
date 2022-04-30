package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/myDay")
    public String myDay(Model model) {
        return "myDay.html";
    }

    @GetMapping("/important")
    public String important(Model model) {
        return "important";
    }

    @GetMapping("/planned")
    public String planned(Model model) {
        return "planned";
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {
        return "tasks";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
