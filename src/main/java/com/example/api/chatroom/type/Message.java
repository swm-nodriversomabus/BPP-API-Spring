package com.example.api.chatroom.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    public enum MessageType{
        ENTER,TALK
    }
    private MessageType type;
    private String sender;
    private String roomId;
    private Object message;

    public void setSender(String sender){
        this.sender = sender;
    }

}
