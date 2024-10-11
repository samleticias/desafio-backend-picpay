package com.example.desafiopicpay.services;

import com.example.desafiopicpay.domain.entities.Transaction;
import com.example.desafiopicpay.domain.entities.User;
import com.example.desafiopicpay.domain.repositories.TransactionRepository;
import com.example.desafiopicpay.rest.dtos.TransactionDTO;
import com.example.desafiopicpay.services.exceptions.UnauthorizedTransaction;
import com.example.desafiopicpay.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    public Transaction insertTransaction (TransactionDTO transactionDTO) throws UnauthorizedTransaction, UserNotFoundException {
        User sender = userService.findUserById(transactionDTO.senderId());
        User receiver = userService.findUserById(transactionDTO.receiverId());

        userService.checkTransaction(sender, transactionDTO.value());

        boolean isPermitted = this.authorizeTransaction(sender, transactionDTO.value());

        if (!isPermitted){
            throw new UnauthorizedTransaction("Transação não autorizada.");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        this.transactionRepository.save(transaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        return transaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        return authorizationResponse.getStatusCode() == HttpStatus.OK &&
                "success".equalsIgnoreCase((String) authorizationResponse.getBody().get("status"));
    }

}
