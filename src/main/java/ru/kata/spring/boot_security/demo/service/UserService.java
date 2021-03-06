package ru.kata.spring.boot_security.demo.service;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService {
    List<User> listUser();
    void add(User user);
    void delete(Long id);

    User findUserByUsername(String username);

    User findUserById(Long id);

}
