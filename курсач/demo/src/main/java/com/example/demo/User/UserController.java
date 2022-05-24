package com.example.demo.User;

import com.example.demo.Task.Task;
import com.example.demo.Task.TaskService;
import com.example.demo.User.Security.SecurityService;
import com.example.demo.User.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final SecurityService securityService;

    @Autowired
    public UserController(UserService userService, UserValidator userValidator, SecurityService securityService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.securityService = securityService;
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(error != null)
        {
            model.addAttribute("error","Username or password is incorrect");
        }


        if (logout !=null)
        {
            model.addAttribute("message", "Logged out successfully");
            return "redirect:/tasks";
        }


        return "login";
    }



    // Передача браузеру страницы с формой
    @RequestMapping(value = {"/registration"}, method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    // Обработка данных формы
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult result, Model model) {

        // Валидация с помощью валидатора
        userValidator.validate(userForm, result);

        // Если есть ошибки - показ формы с сообщениями об ошибках
        if (result.hasErrors()) {
            return "registration";
        }

        // Сохранение пользователя в базе
        userService.save(userForm);

        securityService.manualLogin(userForm.getUsername(),userForm.getPasswordConfirm());

        // Перенаправление на приветственную страницу
        return "redirect:/tasks";
    }


}
