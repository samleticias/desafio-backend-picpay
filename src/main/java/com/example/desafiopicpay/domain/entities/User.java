package com.example.desafiopicpay.domain.entities;

import com.example.desafiopicpay.domain.entities.enums.UserCategory;
import com.example.desafiopicpay.rest.dtos.UserDTO;
import jakarta.persistence.*;
import java.math.BigDecimal;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserCategory userCategory;

    public User(UserDTO userDTO){
        this.firstName = userDTO.firstName();
        this.lastName = userDTO.lastName();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.phone = userDTO.phone();
        this.userCategory = userDTO.userCategory();
        this.balance = userDTO.balance();
        this.document = userDTO.document();
    }
}
