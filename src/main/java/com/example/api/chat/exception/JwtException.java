package com.example.api.chat.exception;

public class JwtException extends RuntimeException{
    public JwtException(String message) {
        super(message);
    }
}
