package com.example.api.user.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum UserGenderEnum {
    None("없음", 0),
    Female("여자", 1),
    Male("남자", 2);

    private final String gender;
    private final Integer genderCode;
}