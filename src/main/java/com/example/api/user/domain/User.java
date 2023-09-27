package com.example.api.user.domain;

import com.example.api.social.adapter.out.persistence.SocialEntity;
import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;
    private String username;
    private UserGenderEnum gender;
    private Integer age;
    private String phone;
    private UserRoleEnum role;
    private Boolean blacklist;
    private String stateMessage;
    private Integer mannerScore;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;
    private SocialEntity socialId;
}