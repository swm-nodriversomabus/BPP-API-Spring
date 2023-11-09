package com.example.api.member.service;

import com.example.api.chatroom.application.port.out.RetrieveChatRoomPort;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.matching.application.port.out.FindMatchingPort;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.member.application.port.in.AddMemberChatRoomUsecase;
import com.example.api.member.application.port.out.AddMemberChatRoomPort;
import com.example.api.member.domain.Member;
import com.example.api.member.dto.AddMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements AddMemberChatRoomUsecase {
    private final FindMatchingPort findMatchingPort;
    private final AddMemberChatRoomPort addMemberChatRoomPort;
    private final RetrieveChatRoomPort retrieveChatRoomPort;
    
    /**
     * 채팅방에 멤버 추가
     * @param addMemberDto (데이터)
     */
    @Override
    @Transactional
    public void addMember(AddMemberDto addMemberDto) {
        List<Member> members = new ArrayList<>();
        ChatRoom chatRoom = retrieveChatRoomPort.retrieveChatRoom(addMemberDto.getChatroomId());
        for (UUID userId: addMemberDto.getMemberIds()) {
            Member member = Member.builder()
                    .chatroomId(chatRoom.getChatroomId())
                    .userId(userId)
                    .outAt(null)
                    .build();
            members.add(member);
        }
        addMemberChatRoomPort.addMember(members, chatRoom);
    }

    /**
     * createMatchingApplication Step 3
     * @param matchingApplication (데이터)
     * @param chatRoom (데이터)
     */
    @Override
    @Transactional
    public ChatRoom setupMatchingChatRoom(MatchingApplication matchingApplication, ChatRoom chatRoom) {
        List<UUID> memberIds = new ArrayList<>();
        memberIds.add(matchingApplication.getUserId());
        UUID matchingWriterId = findMatchingPort.getByMatchingId(matchingApplication.getMatchingId())
                .orElseThrow(NoSuchElementException::new)
                .getWriterId();
        memberIds.add(matchingWriterId);

        AddMemberDto addMemberDto = AddMemberDto.builder()
                .chatroomId(chatRoom.getChatroomId())
                .memberIds(memberIds)
                .build();
        this.addMember(addMemberDto);

        chatRoom.setMembers(memberIds);
        return chatRoom;
    }
}