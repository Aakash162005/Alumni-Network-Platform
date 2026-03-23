package com.alumninetwork.controller;

import com.alumninetwork.model.Message;
import com.alumninetwork.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }

    @GetMapping("/history")
    public List<Message> getHistory(@RequestParam("user1") Long user1, @RequestParam("user2") Long user2) {
        return messageService.getChatHistory(user1, user2);
    }
}
