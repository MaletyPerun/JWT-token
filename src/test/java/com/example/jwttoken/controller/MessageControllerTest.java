package com.example.jwttoken.controller;

import com.example.jwttoken.security.AuthRequest;
import com.example.jwttoken.security.AuthResponse;
import com.example.jwttoken.to.MessageTo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        MessageTo mes = new MessageTo("user", "Lorem ipsum");
        ResponseEntity<String> response = restTemplate.exchange("/message", HttpMethod.POST, new HttpEntity<>(mes, headers), String.class);
        assertEquals("[{\"textMessage\":\"Lorem ipsum\"}]", response.getBody());
    }

    @Test
    public void sendMessageWithEmptyText() {
        AuthResponse authResponse = getAuthHeaderForUser("user", "password");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer_" + authResponse.getJwtToken());
        MessageTo mes = new MessageTo("user", "");
        ResponseEntity<String> response = restTemplate.exchange("/message", HttpMethod.POST, new HttpEntity<>(mes, headers), String.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void loadHistory() {
        AuthResponse authResponse = getAuthHeaderForUser("user", "password");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer_" + authResponse.getJwtToken());
        MessageTo mes = new MessageTo("user", "history 2");
        ResponseEntity<String> response = restTemplate.exchange("/message", HttpMethod.POST, new HttpEntity<>(mes, headers), String.class);
        assertEquals("[{\"textMessage\":\"Lorem ipsum 2\"},{\"textMessage\":\"Lorem ipsum 1\"}]", response.getBody());
    }

    @Test
    public void loadHistoryWithNotInt() {
        AuthResponse authResponse = getAuthHeaderForUser("user", "password");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer_" + authResponse.getJwtToken());
        MessageTo mes = new MessageTo("user", "history 2.5");
        ResponseEntity<String> response = restTemplate.exchange("/message", HttpMethod.POST, new HttpEntity<>(mes, headers), String.class);
        assertEquals(response.getStatusCode(), HttpStatus.CONFLICT);
    }

    @Test
    public void loadHistoryWithNegativeInt() {
        AuthResponse authResponse = getAuthHeaderForUser("user", "password");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer_" + authResponse.getJwtToken());
        MessageTo mes = new MessageTo("user", "history -5");
        ResponseEntity<String> response = restTemplate.exchange("/message", HttpMethod.POST, new HttpEntity<>(mes, headers), String.class);
        assertEquals(response.getStatusCode(), HttpStatus.CONFLICT);
    }

    @Test
    public void sendOrLoadForbidden() {
        HttpHeaders headers = new HttpHeaders();
        MessageTo mes = new MessageTo("user", "history 5");
        ResponseEntity<String> response = restTemplate.exchange("/message", HttpMethod.POST, new HttpEntity<>(mes, headers), String.class);
        assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);
    }

    @Test
    public void sendOrLoadWithWrongName() {
        AuthResponse authResponse = getAuthHeaderForUser("user", "password");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer_" + authResponse.getJwtToken());
        MessageTo mes = new MessageTo("admin", "Lorem ipsum");
        ResponseEntity<String> response = restTemplate.exchange("/message", HttpMethod.POST, new HttpEntity<>(mes, headers), String.class);
        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    private AuthResponse getAuthHeaderForUser(String name, String password) {

        AuthRequest authRequest = new AuthRequest();
        authRequest.setName(name);
        authRequest.setPassword(password);

        return restTemplate.postForObject("/authenticate", authRequest, AuthResponse.class);
    }

}
