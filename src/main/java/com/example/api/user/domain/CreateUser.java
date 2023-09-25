package com.example.api.user.domain;

import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateUser {
    private String username;
    private Long socialId;
    private UserGenderEnum gender;
    private Integer age;
    private String phone;
    private UserRoleEnum role;
    private Boolean blacklist;
    private String stateMessage;
    private Integer mannerScore;
    private Boolean isActive;
}