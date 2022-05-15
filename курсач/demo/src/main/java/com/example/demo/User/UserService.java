package com.example.demo.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}