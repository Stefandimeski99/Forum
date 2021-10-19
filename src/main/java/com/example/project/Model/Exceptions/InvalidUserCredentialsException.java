package com.example.project.Model.Exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException() {
        super("You have entered invalid credentials");
    }
}
