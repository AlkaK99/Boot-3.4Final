package ru.kata.spring.boot_security.demo.service;



import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    public UserDetailsService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }






    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<String> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getAuthority());
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername()).password(passwordEncoder.encode(user.getPassword()))
                .roles(roles.toArray(new String[0]))
                .build();

    }
}
