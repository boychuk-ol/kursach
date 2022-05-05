package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository repository;

        @Autowired
        public UserService(UserRepository repository)
        {
            this.repository = repository;
        }

        public List<User> getAllUsers()
        {
            return repository.findAll();
        }
}
