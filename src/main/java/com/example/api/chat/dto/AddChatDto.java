package com.example.api.chat.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class AddChatDto {
    @NotNull
    private UUID roomId;
    
    @NotNull
    @Min(1L)
    private Long senderId;
    
    @NotBlank
    private String content;
    
    @NotNull
    private Boolean image;
    
    @NotNull
    @Min(0)
    private Integer readCount;
}