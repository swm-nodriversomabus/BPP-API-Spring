package com.example.api.user.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum UserRoleType {
    User("일반 유저"),
    Admin("관리자ㄴ");

    private final String role;
}
