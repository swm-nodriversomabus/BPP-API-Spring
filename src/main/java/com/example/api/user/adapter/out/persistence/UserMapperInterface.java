package com.example.api.user.adapter.out.persistence;

import com.example.api.user.domain.ChatUser;
import com.example.api.user.domain.CreateUser;
import com.example.api.user.domain.User;
import com.example.api.user.dto.CreateUserDto;
import com.example.api.user.dto.UserDto;
import com.example.api.user.domain.User;
import com.example.api.user.dto.FindUserDto;
import com.example.api.user.dto.SaveUserDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


// 추후 매핑 이걸로 변경
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapperInterface {



    CreateUser toDomain(CreateUserDto userDto);

    @Mapping(source = "socialId", target = "socialId.socialId")
    UserEntity toEntity(CreateUser user);

    User toDomain(UserDto userDto);
//    @Mapping(source = "userId",target = "userId.userId")
//    UserEntity toEntity(User user);

//    @Mapping(source = "userId.userId", target = "userId")
    @Mapping(source = "socialId", target = "socialId")
    User toDomain(UserEntity userEntity);
//    @Mapping(source = "userId.userId", target = "userId")
    ChatUser toChatDomain(UserEntity userEntity);
//    UserDto toDto(User user);
//    @Mapping(source = "userId.userId", target = "userId")
//    UserDto toDto(UserEntity userEntity);
    User toDomain(SaveUserDto userDto);
    UserEntity toEntity(User user);
//    User toDomain(UserEntity userEntity);
//    ChatUser toChatDomain(UserEntity userEntity);
    FindUserDto toDto(User user);
    FindUserDto toDto(UserEntity userEntity);
}
