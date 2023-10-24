package com.example.api.chatroom.adapter.out.persistence;

import com.example.api.chatroom.domain.ChatRoom;
import com.example.api.chatroom.dto.CreateChatRoomDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChatRoomMapperInterface {
    ChatRoom toDomain(CreateChatRoomDto createChatRoomDto);
    ChatRoomEntity toEntity(ChatRoom chatRoom);
    ChatRoom toDomain(ChatRoomEntity chatRoomEntity);
}