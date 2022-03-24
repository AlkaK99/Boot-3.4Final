package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class LoginController {

    @GetMapping("/admin")
    public String adminPage() {
        return "adminList";
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }


}