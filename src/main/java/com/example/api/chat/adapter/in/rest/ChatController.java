package com.example.api.chat.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    @RequestMapping("/chat")
    public String Home(){
        return "wow chat server";
    }
}
