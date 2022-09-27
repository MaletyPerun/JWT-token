package com.example.jwttoken.repository;

import com.example.jwttoken.model.Message;
import com.example.jwttoken.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m ORDER BY m.id DESC LIMIT count:=loadCount")
    List<Message> loadHistory(int loadCount);
}
