package com.example.api.friend.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    private Long userId;
    private Long friendId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}