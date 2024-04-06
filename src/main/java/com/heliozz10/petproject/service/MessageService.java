package com.heliozz10.petproject.service;

import com.heliozz10.petproject.entity.Message;
import com.heliozz10.petproject.entity.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class MessageService {
    public Message sendMessage(Message message, User sender, Set<User> receivers) {
        message.setSender(sender);
        message.setReceivers(receivers);
        return message;
    }
}
