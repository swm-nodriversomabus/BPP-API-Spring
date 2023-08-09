package com.example.api.member.domain;

import com.example.api.chatroom.adapter.out.persistence.ChatRoomEntity;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.member.dto.AddMemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private UUID chatroomId;
    private Long userId;
    private LocalDateTime outAt;

}
