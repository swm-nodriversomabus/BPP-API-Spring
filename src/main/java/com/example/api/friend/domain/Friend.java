package com.example.api.friend.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    private UUID userId;
    private UUID friendId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}