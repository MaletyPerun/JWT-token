package com.example.jwttoken.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    long id;

    @Column(name = "text_message", nullable = false)
    @NotBlank
    private String textMessage;

    // использование forieng key на уровне аннотаций
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private User user;

    public Message(String textMessage, User user) {
        this.textMessage = textMessage;
        this.user = user;
    }

    public Message(long id, String textMessage, User user) {
        this.id = id;
        this.textMessage = textMessage;
        this.user = user;
    }
}
