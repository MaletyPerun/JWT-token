package com.example.jwttoken.error;
// кастомный класс исключения для ошибки аутентификации при не совпадении имени в сообщении и имени извлеченного из токена
public class CustomAuthenticationException extends RuntimeException{
    public CustomAuthenticationException(String msg){
        super(msg);
    }
}
