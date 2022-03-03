package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping()
    public String allUsers(Model model){
        model.addAttribute("users", userService.listUser());
        return "admin";

    }

    @DeleteMapping ("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


    @GetMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("updatedUser", userService.findUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }


    @PatchMapping("/{id}")
    public String updatedUser(User user, @RequestParam("listRoles") List<String> roles){
       user.setRoles(roleService.findRoles(roles));
       userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/add")
    public String newUser(@ModelAttribute("user") User user, Model model){
        model.addAttribute("roles", roleService.getAllRoles());
        return "add";
    }

    @PostMapping("")
    public String addUser (@ModelAttribute("user") User user){
        userService.addUser(user);
            return "redirect:/admin";
        }
}
