package com.example.jwttoken.error;

public class CustomAuthenticationException extends RuntimeException{
    public CustomAuthenticationException(String msg){
        super(msg);
    }
}
