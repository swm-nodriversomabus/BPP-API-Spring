package com.example.api.chatroom.adapter.out.persistence;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberPK implements Serializable {
    private Long chatroomId;
    private Long userId;
}
