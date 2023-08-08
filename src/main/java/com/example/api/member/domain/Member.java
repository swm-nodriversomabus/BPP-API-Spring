package com.example.api.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private UUID chatroomId;
    private Long userId;
    private LocalDateTime outAt;
}
