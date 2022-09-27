package com.example.jwttoken.controller;

import com.example.jwttoken.model.Message;
import com.example.jwttoken.repository.MessageRepository;
import com.example.jwttoken.security.JWTUtil;
import com.example.jwttoken.service.CustomUserDetailsService;
import com.example.jwttoken.service.MessageService;
import com.example.jwttoken.to.MessageTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
//            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                    .path("/message/{id}")
//                    .buildAndExpand(messages.get(0).getId()).toUri();
//            return ResponseEntity.created(uriOfNewResource).body(messages);
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
