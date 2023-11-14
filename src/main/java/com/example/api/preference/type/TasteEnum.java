package com.example.api.preference.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum TasteEnum {
    Cold("찬 음식", 1),
    Hot("뜨거운 음식", 2),
    Fatty("기름진 음식", 3),
    Spicy("매운 음식", 4),
    Scent("향이 강한 음식", 5),
    Fishy("비린 음식", 6),
    Meat("육류", 7);
    
    private final String taste;
    private final Integer tasteCode;
}