package com.bruno.FriendsREST.controller;

import com.bruno.FriendsREST.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/message")
    Message send() {
        return new Message("It works!");
    }

    @PostMapping("/message")
    Message echo(@RequestBody Message message) {
        return message;
    }
}
