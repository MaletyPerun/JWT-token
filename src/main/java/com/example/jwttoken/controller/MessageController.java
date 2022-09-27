package com.example.jwttoken.controller;

import com.example.jwttoken.model.Message;
import com.example.jwttoken.service.MessageService;
import com.example.jwttoken.to.MessageTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService service;

    @PostMapping(value = "/message", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> sendMessage(@RequestBody MessageTo mesTo) {
        if (mesTo.getTextMessage().startsWith("history ")) {
            return service.loadHistory(mesTo.getTextMessage().split(" "));
        } else {
            return Arrays.asList(service.save(mesTo));
        }
    }

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/user")
    public String user() {
        return "User";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }

}
