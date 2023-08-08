package com.example.api.member.service;

import com.example.api.member.application.port.in.AddMemberChatRoomUsecase;
import com.example.api.member.application.port.out.AddMemberChaatRoomPort;
import com.example.api.member.domain.Member;
import com.example.api.member.dto.AddMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements AddMemberChatRoomUsecase {
    private final AddMemberChaatRoomPort addMemberChaatRoomPort;
    /**
     * chatroom에 해당 멤버 추가
     * @param addMemberDto
     */
    @Override
    @Transactional
    public void addMember(AddMemberDto addMemberDto) {
        List<Member> members = new ArrayList<>();
        for(Long userId: addMemberDto.getMemberIds()){
            Member member = Member.builder()
                    .chatroomId(addMemberDto.getChatroomId())
                    .userId(userId)
                    .outAt(null)
                    .build();
            members.add(member);
        }
        addMemberChaatRoomPort.addMember(members);

    }
}
