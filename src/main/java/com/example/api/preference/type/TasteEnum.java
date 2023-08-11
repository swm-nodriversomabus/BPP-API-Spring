package com.example.api.preference.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum TasteEnum {
    Cold("찬 음식"),
    Hot("뜨거운 음식"),
    Fatty("기름진 음식"),
    Spicy("매운 음식"),
    Scent("향이 강한 음식"),
    Fishy("비린 음식"),
    Meat("육류");
    
    private final String taste;
}