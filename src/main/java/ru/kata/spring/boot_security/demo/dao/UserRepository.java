package ru.kata.spring.boot_security.demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT user FROM User user JOIN FETCH user.roles where user.username = :username")
    User findUserByUsername(@Param("username") String username);

    @Query(value = "SELECT DISTINCT user FROM User user JOIN FETCH user.roles")
    List<User> findAll();

    @Query(value = "SELECT user FROM User user JOIN FETCH user.roles where user.id = :id")
    User findUserById(@Param("id") long id);

}