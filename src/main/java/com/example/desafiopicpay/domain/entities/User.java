package com.example.desafiopicpay.domain.entities;

import com.example.desafiopicpay.domain.entities.enums.UserCategory;
import jakarta.persistence.*;
import org.hibernate.usertype.UserType;

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
}
