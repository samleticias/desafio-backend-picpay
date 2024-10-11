package com.example.desafiopicpay.services;

import com.example.desafiopicpay.domain.entities.User;
import com.example.desafiopicpay.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
        this.userRepository.save(user);
    }

    public List<User> getAll(){
        return this.userRepository.findAll();
    }
}
