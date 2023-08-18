package com.example.api.chat.dto;

import com.example.api.chat.domain.Chat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AddChatDto {
    @NotNull
    private UUID roomId;
    @NotNull
    private Long senderId;
    @NotNull
    private String content;
    @NotNull
    private Boolean image;
    @NotNull
    private Integer readCount;
}
