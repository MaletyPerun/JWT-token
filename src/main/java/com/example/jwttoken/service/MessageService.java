package com.example.jwttoken.service;

import com.example.jwttoken.model.Message;
import com.example.jwttoken.model.User;
import com.example.jwttoken.repository.MessageRepository;
import com.example.jwttoken.repository.UserRepository;
import com.example.jwttoken.to.MessageTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Message> loadHistory(String[] s) {
        int loadCount;
            try{
                loadCount = Integer.parseInt(s[1]);
            } catch (Exception e) {
            }
            return messageRepository.loadHistory(loadCount);
    }
}
