package com.example.demo.controllers;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service)
    {
        this.service = service;
    }

    @GetMapping("/users")
    public String tasks(Model model)
    {
        model.addAttribute("users", service.getAllUsers());
        return "users";
    }
}
