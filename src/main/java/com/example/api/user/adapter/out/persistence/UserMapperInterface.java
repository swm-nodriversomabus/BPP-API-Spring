package com.example.api.user.adapter.out.persistence;

import com.example.api.user.domain.ChatUser;
import com.example.api.user.domain.User;
import com.example.api.user.dto.UserDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapperInterface {

    User toDomain(UserDto userDto);
    @Mapping(source = "userId",target = "userId.userId")
    UserEntity toEntity(User user);
    User toDomain(UserEntity userEntity);
    ChatUser toChatDomain(UserEntity userEntity);
    UserDto toDto(User user);
    UserDto toDto(UserEntity userEntity);
}