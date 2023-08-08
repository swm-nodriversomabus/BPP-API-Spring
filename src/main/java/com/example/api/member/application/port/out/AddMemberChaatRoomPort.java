package com.example.api.member.application.port.out;

import com.example.api.member.domain.Member;

import java.util.List;

public interface AddMemberChaatRoomPort {
    void addMember(List<Member> members);
}
