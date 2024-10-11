package com.example.desafiopicpay.services.exceptions;

public class UnauthorizedTransaction extends ApplicationException{
    public UnauthorizedTransaction(String message){
        super(message);
    }
}
