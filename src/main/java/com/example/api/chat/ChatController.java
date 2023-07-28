package com.example.api.chat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @RequestMapping("/chat")
    public String Home(){
        return "wow chat server";
    }
}
