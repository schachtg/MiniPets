package com.example.minipets;


public class InvalidNameException extends Exception {
    public InvalidNameException(String errorMessage){
        super(errorMessage);
    }
}
