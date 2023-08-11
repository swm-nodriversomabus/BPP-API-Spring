package com.example.api.preference.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum PreferSmokeEnum {
    None("선호 없음"),
    Nonsmoke("비흡연"),
    Smoke("흡연");
    
    private final String preferSmoke;
}