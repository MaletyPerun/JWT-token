package com.example.jwttoken.repository;

import com.example.jwttoken.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByLogin(String login);
}
