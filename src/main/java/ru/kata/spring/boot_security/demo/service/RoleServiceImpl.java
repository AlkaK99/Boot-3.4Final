package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Set<Role> findRoles(List<String> roles) {
        Set<Role> getAllRoles = new HashSet<>();
        for (String name : roles) {
            getAllRoles.add(roleRepository.findRoleByName(name));
        }
        return getAllRoles;
    }
}
