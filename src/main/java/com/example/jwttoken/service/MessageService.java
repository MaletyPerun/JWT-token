package com.example.jwttoken.service;

import com.example.jwttoken.DataConflictException;
import com.example.jwttoken.model.Message;
import com.example.jwttoken.model.User;
import com.example.jwttoken.repository.MessageRepository;
import com.example.jwttoken.repository.UserRepository;
import com.example.jwttoken.to.MessageTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    // TODO: 27.09.2022 расставить отловку исключений на валидацию
    public Message save(MessageTo mesTo) {

        String userName = mesTo.getUserName();
        User user = userRepository.findByName(userName);
        Message message = new Message(mesTo.getTextMessage(), user);
        return messageRepository.save(message);
    }

    public List<Message> loadHistory(String[] s) {
        int loadCount = Arrays.stream(s).skip(1)
                .mapToInt(Integer::parseInt)
                .findFirst()
                .orElseThrow(() -> new DataConflictException("вводимое число должно быть целым и положительным"));

        if (loadCount <= 0) {
            throw new DataConflictException("вводимое число должно быть целым и положительным");
        }

        List<Message> messages = messageRepository.loadHistory(loadCount);
        return messages.stream()
                .limit(loadCount)
                .collect(Collectors.toList());
    }
}
