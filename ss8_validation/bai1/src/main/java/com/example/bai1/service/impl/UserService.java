package com.example.bai1.service.impl;

import com.example.bai1.model.User;
import com.example.bai1.repository.IUserRepository;
import com.example.bai1.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public void add(User user) {
        userRepository.save(user);
    }
}
