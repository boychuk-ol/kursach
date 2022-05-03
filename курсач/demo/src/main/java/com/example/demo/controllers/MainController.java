package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("title","My day");
        return "myDay";
    }

    @GetMapping("/important")
    public String important(Model model) {
        model.addAttribute("title","Important");
        return "important";}

    @GetMapping("/planned")
    public String planned(Model model) {
        model.addAttribute("title","Planned");
        return "planned";
    }

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/tasks")
    public String tasks(Model model) {
        model.addAttribute("title","Tasks");
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);
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
