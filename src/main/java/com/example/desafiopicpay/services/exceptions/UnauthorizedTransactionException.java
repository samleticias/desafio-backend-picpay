package com.example.desafiopicpay.services.exceptions;

public class UnauthorizedTransactionException extends ApplicationException{
    public UnauthorizedTransactionException(String message){
        super(message);
    }
}

