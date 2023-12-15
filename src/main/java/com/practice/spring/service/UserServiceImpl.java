package com.practice.spring.service;

import com.practice.spring.dao.UserRepository;
import com.practice.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository) {
        this.userRepository = theUserRepository;
    }

    @Override
    public Optional<User> findById(int theId) {
        return userRepository.findById(theId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User User) {
        return userRepository.save(User);
    }

    @Override
    public void delete(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password){
        User accountFound = userRepository.findByUsernameAndPassword(username, password);
        return accountFound;
    }
}
