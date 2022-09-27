package com.example.jwttoken.to;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MessageTo {

    @NotBlank
    private String userName;

    @NotBlank
    private String textMessage;
}
