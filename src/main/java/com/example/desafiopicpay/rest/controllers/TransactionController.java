package com.example.desafiopicpay.rest.controllers;

import com.example.desafiopicpay.domain.entities.Transaction;
import com.example.desafiopicpay.rest.dtos.TransactionDTO;
import com.example.desafiopicpay.services.TransactionService;
import com.example.desafiopicpay.services.exceptions.UnauthorizedTransaction;
import com.example.desafiopicpay.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction (@RequestBody TransactionDTO transactionDTO) throws UserNotFoundException, UnauthorizedTransaction {
        Transaction transaction = transactionService.insertTransaction(transactionDTO);
        return ResponseEntity.ok(transaction);
    }

}
