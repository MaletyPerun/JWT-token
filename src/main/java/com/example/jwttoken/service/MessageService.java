package com.example.jwttoken.service;

import com.example.jwttoken.model.Message;
import com.example.jwttoken.model.User;
import com.example.jwttoken.repository.MessageRepository;
import com.example.jwttoken.repository.UserRepository;
import com.example.jwttoken.to.MessageTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    public Message save(MessageTo mesTo) {
        String userName = mesTo.getUserName();
        User user = userRepository.findByName(userName);
        Message message = new Message(mesTo.getTextMessage(), user);
        return messageRepository.save(message);
    }
}
