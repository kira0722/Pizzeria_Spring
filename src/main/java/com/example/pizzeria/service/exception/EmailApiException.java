package com.example.pizzeria.service.exception;

public class EmailApiException extends RuntimeException{

    public EmailApiException(){
        super("Error sending email...");
    }
}
