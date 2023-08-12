package com.example.api.member.application.port.in;

import com.example.api.member.dto.AddMemberDto;

import java.util.List;

public interface AddMemberChatRoomUsecase {
    void addMember(AddMemberDto addMemberDto);
}
