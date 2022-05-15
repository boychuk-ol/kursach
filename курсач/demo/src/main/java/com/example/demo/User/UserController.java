package com.example.demo.User;

import com.example.demo.User.Security.SecurityService;
import com.example.demo.User.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @GetMapping("/log")
    public String login(Model model)
    {
        return "login";
    }


    @RequestMapping(value = {"/allTasks"},method = RequestMethod.GET)
    public String allTasks(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String s = authentication.getName();

        model.addAttribute("username", s);
        return "allTasks";
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
        return "redirect:/allTasks";
    }


}
