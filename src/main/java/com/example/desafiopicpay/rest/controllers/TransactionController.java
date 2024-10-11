package com.example.desafiopicpay.rest.controllers;

import com.example.desafiopicpay.domain.entities.Transaction;
import com.example.desafiopicpay.rest.dtos.TransactionDTO;
import com.example.desafiopicpay.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction (@RequestBody TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = transactionService.insertTransaction(transactionDTO);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> transactions = transactionService.getAll();
        return ResponseEntity.ok(transactions);
    }

}
