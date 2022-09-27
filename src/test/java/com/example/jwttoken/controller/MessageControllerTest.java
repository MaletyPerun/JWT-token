package com.example.jwttoken.controller;

import com.example.jwttoken.security.AuthRequest;
import com.example.jwttoken.security.AuthResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void sendMessage() {
        AuthResponse authResponse = getAuthHeaderForUser("user", "password");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer_" + authResponse.getJwtToken());
        MultiValueMap<String, String> body = new LinkedMultiValueMap();
        body.add("userName", "user");
        body.add("textMessage", "Lorem ipsum");
        ResponseEntity<String> response = restTemplate.exchange("/message", HttpMethod.POST, new HttpEntity<>(body, headers), String.class);
        assertTrue(response.getBody().equals("Lorem ipsum"));
    }


    @Test
    public void whenGetUser_thenCorrect() {

        AuthResponse authResponse = getAuthHeaderForUser("user", "password");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer_" + authResponse.getJwtToken());

        ResponseEntity<String> response = restTemplate.exchange("/user", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        assertTrue(response.getBody().equals("User"));

    }

    @Test
    public void whenGetAdmin_thenCorrect() {


        AuthResponse authResponse = getAuthHeaderForUser("admin", "password");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer_" + authResponse.getJwtToken());

        ResponseEntity<String> response = restTemplate.exchange("/admin", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        assertTrue(response.getBody().equals("Admin"));

    }

    @Test
    public void givenUserCredentials_whenGetAdmin_thenCorrect() {


        AuthResponse authResponse = getAuthHeaderForUser("user", "password");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer_" + authResponse.getJwtToken());

        ResponseEntity<String> response = restTemplate.exchange("/admin", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);

    }

    private AuthResponse getAuthHeaderForUser(String name, String password) {

        AuthRequest authRequest = new AuthRequest();
        authRequest.setName(name);
        authRequest.setPassword(password);
        AuthResponse authResponse = restTemplate.postForObject("/authenticate", authRequest, AuthResponse.class);

        return authResponse;
    }

}
