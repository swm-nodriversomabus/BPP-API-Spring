package com.example.api.chatroom.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum ChatRoomEnum {
    Normal("일반", 1),
    Matching("매칭", 2),
    Inquiry("상담", 3);

    private final String type;
    private final Integer typeCode;
}