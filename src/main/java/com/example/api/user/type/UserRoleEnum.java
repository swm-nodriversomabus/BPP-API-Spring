package com.example.api.user.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum UserRoleEnum {
    Admin("관리자", 1),
    User("일반 사용자", 2);

    private final String role;
    private final Integer roleCode;
}