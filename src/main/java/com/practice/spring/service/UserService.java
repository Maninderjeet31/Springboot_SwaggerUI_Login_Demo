package com.practice.spring.service;

import com.practice.spring.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(int theId);
    List<User> findAll();
    User save(User User);
    void delete(int theId);
    User findByUsernameAndPassword(String username, String password);
}
