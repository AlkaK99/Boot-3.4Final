package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;



@Service
public class UserServiceImpl implements UserService{



    PasswordEncoder passwordEncoder;

    UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public void add(User user) {
        userRepository.save(user);

    }

    @Override
    public void delete (Long id) {
        userRepository.delete(userRepository.findUserById(id));
    }


    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }
}