package com.example.api.chat.adapter.out.persistence;

import com.example.api.chat.domain.Chat;
import com.example.api.chat.dto.AddChatDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE) // 스프링과 사용할 때는 필수적으로 붙여주자, 일치하지 않는 필드는 무ㅡ
public interface ChatMapper {
    @Mapping(source = "roomId", target = "roomId.chatroomId")
    @Mapping(source = "senderId",target = "senderId.userId")
    ChatEntity toEntity(AddChatDto addChatDto);


    @Mapping(source = "roomId.chatroomId", target = "roomId")
    @Mapping(source = "senderId.userId", target = "senderId")
    AddChatDto toDto(ChatEntity chatEntity);

    @Mapping(source = "roomId.chatroomId", target = "roomId")
    Chat toDomain(ChatEntity chatEntity);

    @Mapping(source = "senderId",target = "senderId.userId")
    Chat toDomain(AddChatDto addChatDto);
}
