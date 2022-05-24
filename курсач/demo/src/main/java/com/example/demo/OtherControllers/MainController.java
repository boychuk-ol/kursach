package com.example.demo.OtherControllers;


import com.example.demo.Task.Task;
import com.example.demo.Task.TaskService;
import com.example.demo.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class MainController {




    @GetMapping("/test")
    public String test()
    {
        return "124124";
    }



    @GetMapping("/planned")
    public String planned(Model model)
    {
        model.addAttribute("title", "Planned");
        return "testhome";
    }


}
