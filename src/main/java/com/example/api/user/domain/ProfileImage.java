package com.example.api.user.domain;

import com.example.api.common.type.ApplicationStateEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileImage {
    private UUID userId;
    private String profileImage;
    private ApplicationStateEnum state;
    private LocalDateTime updatedAt;
}