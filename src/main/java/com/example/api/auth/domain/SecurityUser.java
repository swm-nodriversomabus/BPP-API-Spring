package com.example.api.auth.domain;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class SecurityUser {
    private UUID userId;

    private String naverId;

    private String kakaoId;

    private String googleId;

    private String instaId;

    private String appleId;

    private String role;
}
