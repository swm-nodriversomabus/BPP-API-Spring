package com.example.api.chatroom.dto;

import com.example.api.chatroom.type.ChatRoomEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateChatRoomDto {
    @NotNull
    private ChatRoomEnum type;
    
    @NotBlank
    private String chatroomName;
    
    @NotNull
    private Boolean isActive;
    
    @NotNull
    private Long masterId; // TODO 추후 jwt에서 얻을 수 있어 삭제 가능
}