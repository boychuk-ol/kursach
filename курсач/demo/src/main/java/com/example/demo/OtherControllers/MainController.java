package com.example.demo.OtherControllers;

import com.example.demo.User.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
        return "important";
    }

    @GetMapping("/planned")
    public String planned(Model model) {
        model.addAttribute("title","Planned");
        return "planned";
    }



}
