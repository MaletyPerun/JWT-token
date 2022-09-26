package com.example.jwttoken.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

//    @Column(name = "position", nullable = false)
//    @NotBlank
//    private String position;

    @Column(name = "role", nullable = false)
    @NotBlank
    private String role;
}
