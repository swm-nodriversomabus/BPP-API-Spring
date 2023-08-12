package com.example.api.chatroom.dto;

import com.example.api.chatroom.type.ChatRoomType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateChatRoomDto {
    @NotEmpty
    private String chatroomName;

    @NotEmpty
    private ChatRoomType type;
    
    @NotEmpty
    private Boolean isActive;
}