package com.example.api.chatroom.adapter.out.persistence;

import com.example.api.chatroom.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatRoomMapper {

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
                .build();
    }
    public ChatRoomEntity fromDomainToEntity(ChatRoom chatRoom){

        return ChatRoomEntity.builder()
                .chatroomName(chatRoom.getChatroomName())
                .type(chatRoom.getType())
                .masterId(chatRoom.getMasterId())
                .isActive(chatRoom.getIsActive())
                .build();
    }

    public ChatRoom fromEntityToDomain(ChatRoomEntity chatRoomEntity){
        return ChatRoom.builder()
                .chatroomName(chatRoomEntity.getChatroomName())
                .type(chatRoomEntity.getType())
                .masterId(chatRoomEntity.getMasterId())
                .isActive(chatRoomEntity.getIsActive())
                .chatroomId(chatRoomEntity.getChatroomId())
                .build();
    }
}
