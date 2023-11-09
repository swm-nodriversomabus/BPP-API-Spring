package com.example.api.user.domain;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatUser {
    private UUID userId;
    private String username;
    private String nickname;
}