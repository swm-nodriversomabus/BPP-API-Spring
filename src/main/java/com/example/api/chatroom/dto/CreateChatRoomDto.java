package com.example.api.chatroom.dto;

import com.example.api.chatroom.type.ChatRoomType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateChatRoomDto {

    @NotNull()
    private ChatRoomType type;
    @NotNull
    private String chatroomName;
    @NotNull
    private Boolean isActive;
}
