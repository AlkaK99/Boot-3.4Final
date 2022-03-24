package ru.kata.spring.boot_security.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/api")
public class RESTController {

    private final UserService userService;
    private final RoleService roleService;

    public RESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> roles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/show")
    public ResponseEntity<User> show() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(userService.findUserByUsername(name));
    }

    @GetMapping("/findUserById/{id}")
    public ResponseEntity<User> findUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/ListUser")
    public ResponseEntity<List<User>> listUser() {
        return ResponseEntity.ok(userService.listUser());
    }

    @PostMapping("/add")
    public ResponseEntity<List<User>> add(@RequestBody User user) {
        userService.add(user);
        return ResponseEntity.ok(userService.listUser());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<List<User>> update(@RequestBody User user, @PathVariable long id) {
        User updatedUser = userService.findUserById(id);
        updatedUser.setFirstname(user.getFirstname());
        updatedUser.setLastname(user.getLastname());
        updatedUser.setAge(user.getAge());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setRoles(user.getRoles());
        userService.add(updatedUser);
        return ResponseEntity.ok(userService.listUser());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<User>> delete(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.ok(userService.listUser());
    }


}