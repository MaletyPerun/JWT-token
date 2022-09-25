package com.example.jwttoken.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "my_user")
@Getter
@Setter
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "login", nullable = false)
    @NotBlank
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @Column(name = "position", nullable = false)
    @NotBlank
    private String position;

    @Column(name = "role", nullable = false)
    @NotBlank
    private String role;
}