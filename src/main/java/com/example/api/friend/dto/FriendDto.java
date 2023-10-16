package com.example.api.friend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FriendDto {
    @NotNull
    private UUID userId;
    
    @NotNull
    private UUID friendId;
}