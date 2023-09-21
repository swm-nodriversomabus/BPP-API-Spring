package com.example.api.user.adapter.out.persistence;

import com.example.api.user.domain.ChatUser;
import com.example.api.user.domain.User;
import com.example.api.user.dto.FindUserDto;
import com.example.api.user.dto.SaveUserDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapperInterface {
    User toDomain(SaveUserDto userDto);
    UserEntity toEntity(User user);
    User toDomain(UserEntity userEntity);
    ChatUser toChatDomain(UserEntity userEntity);
    FindUserDto toDto(User user);
    FindUserDto toDto(UserEntity userEntity);
}