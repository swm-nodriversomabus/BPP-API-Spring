package com.example.api.member.adapter.out.persistence;

import com.example.api.chatroom.adapter.out.persistence.ChatRoomEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class MemberPK implements Serializable {
    private ChatRoomEntity chatroom;
    private Long userId;
}