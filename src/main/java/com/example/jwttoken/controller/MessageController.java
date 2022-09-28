package com.example.jwttoken.controller;

import com.example.jwttoken.model.Message;
import com.example.jwttoken.service.MessageService;
import com.example.jwttoken.to.MessageTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService service;

    @PostMapping(value = "/message", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> sendMessage(@Valid @RequestBody MessageTo mesTo, HttpServletRequest request) {
        if (mesTo.getTextMessage().startsWith("history ")) {
            return service.loadHistory(mesTo.getTextMessage().split(" "));
        } else {
            return Arrays.asList(service.save(mesTo, request));
        }
    }
}
