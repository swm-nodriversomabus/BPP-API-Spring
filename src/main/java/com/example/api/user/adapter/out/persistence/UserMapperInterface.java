package com.example.api.user.adapter.out.persistence;

import com.example.api.user.domain.CreateUser;
import com.example.api.user.domain.ProfileImage;
import com.example.api.user.domain.User;
import com.example.api.user.dto.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapperInterface {
    CreateUser toDomain(CreateUserDto userDto);
    User toDomain(UpdateUserDto userDto);
    @Mapping(source = "socialId", target = "socialId.socialId")
    UserEntity toEntity(CreateUser user);
    UserEntity toEntity(User user);
    @Mapping(source = "socialId", target = "socialId")
    User toDomain(UserEntity userEntity);
    FindUserDto toDto(UserEntity userEntity);
    FindUserDto toDto(User user);
    FindUserInfoDto toInfoDto(UserEntity userEntity);
    UserAuthorityCheckDto toAuthorityDto(UserEntity userEntity);
    FindUserDto toDto(UpdateUserDto updateUserDto);
    ProfileImageEntity toEntity(ProfileImage profileImage);
}