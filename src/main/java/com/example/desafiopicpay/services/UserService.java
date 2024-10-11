package com.example.desafiopicpay.services;

import com.example.desafiopicpay.domain.entities.User;
import com.example.desafiopicpay.domain.repositories.UserRepository;
import com.example.desafiopicpay.rest.dtos.UserDTO;
import com.example.desafiopicpay.services.exceptions.UserNotFoundException;
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

    public User findUserById(Long id) throws UserNotFoundException {
        return this.userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
    }

    public User insertUser(UserDTO userDTO){
        User user = new User(userDTO);
        this.saveUser(user);
        return user;
    }
}
