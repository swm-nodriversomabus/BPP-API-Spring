package com.example.api.friend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FriendDto {
    @NotNull
    private Long userId;
    
    @NotNull
    private Long friendId;
}