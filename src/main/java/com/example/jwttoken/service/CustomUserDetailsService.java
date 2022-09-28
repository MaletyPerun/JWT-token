package com.example.jwttoken.service;

import com.example.jwttoken.model.User;
import com.example.jwttoken.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName){
        User user = repository.findByName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Неизвестный пользователь: " + userName);
        }
        UserDetails userDet = org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        return userDet;
    }
}
