package com.example.jwttoken.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private String jwtToken;

    public AuthResponse() {
    }
}
