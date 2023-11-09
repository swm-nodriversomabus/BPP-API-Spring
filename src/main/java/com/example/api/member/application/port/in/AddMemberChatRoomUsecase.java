package com.example.api.member.application.port.in;

import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.member.dto.AddMemberDto;

public interface AddMemberChatRoomUsecase {
    void addMember(AddMemberDto addMemberDto);
    ChatRoom setupMatchingChatRoom(MatchingApplication matchingApplication, ChatRoom chatRoom);
}