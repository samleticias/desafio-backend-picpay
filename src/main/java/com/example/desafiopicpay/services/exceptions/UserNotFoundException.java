package com.example.desafiopicpay.services.exceptions;

public class UserNotFoundException extends ApplicationException{
    public UserNotFoundException(String message){
        super(message);
    }
}
