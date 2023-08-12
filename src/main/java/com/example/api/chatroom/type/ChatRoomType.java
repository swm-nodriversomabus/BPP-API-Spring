package com.example.api.chatroom.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum ChatRoomType {
    Normal("일반"),
    Inquery("상담");

    private final String type;
}
