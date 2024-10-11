package com.example.desafiopicpay.rest.dtos;

import com.example.desafiopicpay.domain.entities.enums.UserCategory;

import java.math.BigDecimal;

public record UserDTO(
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        String email,
        String password,
        String phone,
        UserCategory userCategory
) {
}
