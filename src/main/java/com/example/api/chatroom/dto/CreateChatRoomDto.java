package com.example.api.chatroom.dto;

import com.example.api.chatroom.type.ChatRoomEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateChatRoomDto {
    @NotNull
    private Long masterId; // TODO 추후 jwt에서 얻을 수 있어 삭제 가능
    
    @NotNull
    private String chatroomName;
    
    @NotNull()
    private ChatRoomEnum type;
    
    @NotNull
    private Boolean isActive;
}