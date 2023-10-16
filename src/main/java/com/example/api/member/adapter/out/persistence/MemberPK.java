package com.example.api.member.adapter.out.persistence;

import com.example.api.chatroom.adapter.out.persistence.ChatRoomEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class MemberPK implements Serializable {
    private ChatRoomEntity chatroom;
    private UUID userId;
}