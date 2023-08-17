package com.example.api.chat.adapter.out.persistence;

import com.example.api.chat.domain.Chat;
import com.example.api.chat.dto.AddChatDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE) // 스프링과 사용할 때는 필수적으로 붙여주자, 일치하지 않는 필드는 무ㅡ
public interface ChatMapper {
    @Mapping(source = "roomId", target = "roomId.chatroomId")
    ChatEntity toEntity(AddChatDto addChatDto);


    @Mapping(source = "roomId.chatroomId", target = "roomId")
    AddChatDto toDto(ChatEntity chatEntity);

    @Mapping(source = "roomId.chatroomId", target = "roomId")
    Chat toDomain(ChatEntity chatEntity);

    AddChatDto toDomain(AddChatDto addChatDto);
}
