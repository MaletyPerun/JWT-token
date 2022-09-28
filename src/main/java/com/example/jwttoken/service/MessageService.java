package com.example.jwttoken.service;

import com.example.jwttoken.error.CustomAuthenticationException;
import com.example.jwttoken.error.DataConflictException;
import com.example.jwttoken.model.Message;
import com.example.jwttoken.model.User;
import com.example.jwttoken.repository.MessageRepository;
import com.example.jwttoken.repository.UserRepository;
import com.example.jwttoken.security.JWTUtil;
import com.example.jwttoken.to.MessageTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    public Message save(MessageTo mesTo, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String jwt = header.substring(7);
        String userJwt = jwtUtil.extractUsername(jwt);

        String userName = mesTo.getUserName();
        if (!userJwt.equals(userName)) {
            throw new CustomAuthenticationException("Неверное введенное имя");
        }
        User user = userRepository.findByName(userName);
        Message message = new Message(mesTo.getTextMessage(), user);
        return messageRepository.save(message);
    }

    public List<Message> loadHistory(String[] s) {
        int loadCount;
        try {
            loadCount = Integer.parseInt(s[1]);
        } catch (Exception e) {
            throw new DataConflictException("Вводимое число должно быть целым и положительным");
        }

        if (loadCount <= 0) {
            throw new DataConflictException("Вводимое число должно быть целым и положительным");
        }

        List<Message> messages = messageRepository.loadHistory(loadCount);
        return messages.stream()
                .limit(loadCount)
                .collect(Collectors.toList());
    }
}
