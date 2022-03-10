package ru.kata.spring.boot_security.demo.controller;


import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }


    @GetMapping()
    public String allUsers(@ModelAttribute("user") User user, Model model){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("listUser", userService.listUser());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userService.findUserByUsername(name));
        return "adminList";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }



    @PatchMapping("/{id}")
    public String updatedUser(User user, @RequestParam("listRoles") List<String> listRoles){
       user.setRoles(roleService.findRoles(listRoles));
       userService.addUser(user);
        return "redirect:/admin";

    }

    @PostMapping()
    public String addUser (@Valid User user, @RequestParam("listRoles") List<String> listRoles){

        user.setRoles(roleService.findRoles(listRoles));
        userService.addUser(user);
            return "redirect:/admin";
        }
}
