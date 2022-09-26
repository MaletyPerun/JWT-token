package com.example.jwttoken.repository;

import com.example.jwttoken.model.Message;
import com.example.jwttoken.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
