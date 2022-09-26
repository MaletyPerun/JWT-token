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

@RestController
public class MessageController {

    @Autowired
    private MessageService service;

//    @Autowired
//    private JWTUtil jwtTokenUtil;

    @PostMapping(value = "/message", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> sendMessage(@RequestBody MessageTo mesTo) {
        Message created = service.save(mesTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
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
