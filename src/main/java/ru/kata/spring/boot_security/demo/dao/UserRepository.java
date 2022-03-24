package ru.kata.spring.boot_security.demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT DISTINCT user FROM User user ORDER BY user.id")
    List<User> findAll();

    User findUserByUsername(String username);
    User findUserById(long id);

}