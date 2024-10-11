package com.example.desafiopicpay.rest.controllers;

import com.example.desafiopicpay.domain.entities.User;
import com.example.desafiopicpay.rest.dtos.UserDTO;
import com.example.desafiopicpay.services.UserService;
import com.example.desafiopicpay.services.exceptions.EmailAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) throws EmailAlreadyExistsException {
        User user = userService.insertUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }
}
