package com.example.api.user.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatUser {
    private Long userId;
    private String username;
    private String nickname;
}