package com.example.api.chatroom.dto;

import com.example.api.chatroom.type.ChatRoomEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateChatRoomDto {
//    @NotNull
    private UUID masterId;
    
    @NotBlank
    private String chatroomName;
    
    @NotNull
    private ChatRoomEnum type;
    
    @NotNull
    private Boolean isActive;
}