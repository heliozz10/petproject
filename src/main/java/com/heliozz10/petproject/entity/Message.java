package com.heliozz10.petproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;

//TODO: Implement message service
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "message")
public class Message {
    @Setter(AccessLevel.NONE)
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title = "No title";

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToMany
    @JoinTable(name = "message_receivers",
    joinColumns = @JoinColumn(name = "message_id"),
    inverseJoinColumns = @JoinColumn(name = "receiver_id"))
    private Set<User> receivers;

    public Message(String title, String content, User sender, Set<User> receivers) {
        this(title, content);
        this.sender = sender;
        this.receivers = receivers;
    }

    public Message(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
