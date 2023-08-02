package com.example.api.user.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum UserGenderType {
    None("없음"),
    Female("여자"),
    Male("남자");

    private final String gender;
}