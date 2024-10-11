package com.example.desafiopicpay.rest.dtos;

import java.math.BigDecimal;

public record TransactionDTO(
        Long senderId,
        Long receiverId,
        BigDecimal value
) {
}
