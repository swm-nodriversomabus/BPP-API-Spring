package com.example.api.chatroom.adapter.out.persistence;

import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.member.adapter.out.persistence.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatRoomMapper {
    private final MemberMapper memberMapper;
    /**
     * 챗룸을 만들 때 사용할 예정
     * domain을 엔티티로 매핑
     * @param chatRoom
     * @return chatRoomEntity
     */
    public ChatRoomEntity fromDomainToEntityWithoutId(ChatRoom chatRoom){

        return ChatRoomEntity.builder()
                .chatroomName(chatRoom.getChatroomName())
                .type(chatRoom.getType())
                .masterId(chatRoom.getMasterId())
                .isActive(chatRoom.getIsActive())
                .members(new ArrayList<>())
                .build();
    }
    public ChatRoomEntity fromDomainToEntity(ChatRoom chatRoom){

        return ChatRoomEntity.builder()
                .chatroomId(chatRoom.getChatroomId())
                .chatroomName(chatRoom.getChatroomName())
                .type(chatRoom.getType())
                .masterId(chatRoom.getMasterId())
                .isActive(chatRoom.getIsActive())
                .members(new ArrayList<>())
                .build();
    }

    public ChatRoom fromEntityToDomain(ChatRoomEntity chatRoomEntity){
        return ChatRoom.builder()
                .chatroomName(chatRoomEntity.getChatroomName())
                .type(chatRoomEntity.getType())
                .masterId(chatRoomEntity.getMasterId())
                .isActive(chatRoomEntity.getIsActive())
                .members(memberMapper.fromListEntityToDomain(chatRoomEntity.getMembers()))
                .chatroomId(chatRoomEntity.getChatroomId())
                .build();
    }

    public List<ChatRoom> fromEntityListToDomain(List<ChatRoomEntity> chatRoomEntityList){
        List<ChatRoom> chatRoomList = new ArrayList<>();
        chatRoomEntityList.forEach(chatRoomEntity -> chatRoomList.add(
                ChatRoom.builder()
                        .chatroomName(chatRoomEntity.getChatroomName())
                        .type(chatRoomEntity.getType())
                        .masterId(chatRoomEntity.getMasterId())
                        .members(memberMapper.fromListEntityToDomain(chatRoomEntity.getMembers()))
                        .chatroomId(chatRoomEntity.getChatroomId())
                        .isActive(chatRoomEntity.getIsActive())
                        .build()
        ));
        return chatRoomList;
    }
}
