package com.example.api.member.adapter.out.persistence;

import com.example.api.chatroom.adapter.out.persistence.ChatRoomEntity;
import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.member.domain.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberMapper {
    /**
     * domain -> entity
     * @param member
     * @return memberentity
     */
    public MemberEntity fromDomainToEntity(Member member, ChatRoomEntity chatRoomEntity) {
        return MemberEntity.builder()
                .chatroom(chatRoomEntity)
                .userId(member.getUserId())
                .outAt(member.getOutAt())
                .build();
    }

    public List<Member> fromListEntityToDomain(List<MemberEntity> membersList) {
        List<Member> memberList = new ArrayList<>();
        membersList.forEach(memberEntity -> {
            ChatRoomEntity chatRoomEntity = memberEntity.getChatroom();
            memberList.add(
                    Member.builder()
                            .userId(memberEntity.getUserId())
                            .outAt(memberEntity.getOutAt())
                            .chatroomId(chatRoomEntity.getChatroomId())
                            .build()
            );
        });
        return memberList;
    }

    /**
     * List로 들어오는 도메인을 반환
     * @param members
     * @return List<MemberEntity>
     */
    public List<MemberEntity> fromListDomainToEntity(List<Member> members, ChatRoomEntity chatRoomEntity) {
        List<MemberEntity> memberEntities = new ArrayList<>();
        for (Member member: members) {
            memberEntities.add(
                    MemberEntity.builder()
                            .chatroom(chatRoomEntity)
                            .userId(member.getUserId())
                            .outAt(member.getOutAt())
                            .build()
            );
        }
        return memberEntities;
    }

    public Member fromEntityToDomain(MemberEntity memberEntity, ChatRoom chatRoom) {
        return Member.builder()
                .chatroomId(chatRoom.getChatroomId())
                .userId(memberEntity.getUserId())
                .outAt(memberEntity.getOutAt())
                .build();
    }
}