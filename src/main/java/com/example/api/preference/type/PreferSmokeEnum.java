package com.example.api.preference.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum PreferSmokeEnum {
    None("선호 없음", 0),
    Nonsmoke("비흡연", 1),
    Smoke("흡연", 2);
    
    private final String preferSmoke;
    private final Integer smokeCode;
}