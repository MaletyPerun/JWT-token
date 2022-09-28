package com.example.jwttoken.error;
//класс исключения при ошибке парсинга числа в случае "history 2 / 2.5 / -5"
public class DataConflictException extends RuntimeException {
    public DataConflictException(String msg) {
        super(msg);
    }
}