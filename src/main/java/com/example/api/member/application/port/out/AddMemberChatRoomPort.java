package com.example.api.member.application.port.out;

import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.member.domain.Member;

import java.util.List;

public interface AddMemberChatRoomPort {
    void addMembers(List<Member> members, ChatRoom chatRoom);
}