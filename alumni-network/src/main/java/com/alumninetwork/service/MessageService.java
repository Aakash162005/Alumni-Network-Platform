package com.alumninetwork.service;

import com.alumninetwork.model.Message;
import com.alumninetwork.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getChatHistory(Long user1, Long user2) {
        return messageRepository.findChatHistory(user1, user2);
    }
}
