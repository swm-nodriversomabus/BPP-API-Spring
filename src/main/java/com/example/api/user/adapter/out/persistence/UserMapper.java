package com.example.api.user.adapter.out.persistence;

import com.example.api.user.domain.ChatUser;
import com.example.api.user.domain.User;
import com.example.api.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class UserMapper {
    public User fromDtoToDomain(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .nickname(userDto.getNickname())
                .gender(userDto.getGender())
                .age(userDto.getAge())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .role(userDto.getRole())
                .blacklist(userDto.getBlacklist())
                .personality(userDto.getPersonality())
                .stateMessage(userDto.getStateMessage())
                .mannerScore(userDto.getMannerScore())
                .createdUserId(userDto.getCreatedUserId())
                .updatedUserId(userDto.getUpdatedUserId())
                .isActive(userDto.getIsActive())
                .build();
    }

    public UserEntity fromDomainToEntity(User user) {
        return UserEntity.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .age(user.getAge())
                .phone(user.getPhone())
                .email(user.getEmail())
                .address(user.getAddress())
                .role(user.getRole())
                .blacklist(user.getBlacklist())
                .personality(user.getPersonality())
                .stateMessage(user.getStateMessage())
                .mannerScore(user.getMannerScore())
                .createdUserId(user.getCreatedUserId())
                .updatedUserId(user.getUpdatedUserId())
                .isActive(user.getIsActive())
                .build();
    }
    
    public User fromEntityToDomain(UserEntity userEntity) {
        return User.builder()
                .userId(userEntity.getUserId())
                .username(userEntity.getUsername())
                .nickname(userEntity.getNickname())
                .gender(userEntity.getGender())
                .age(userEntity.getAge())
                .phone(userEntity.getPhone())
                .email(userEntity.getEmail())
                .address(userEntity.getAddress())
                .role(userEntity.getRole())
                .blacklist(userEntity.getBlacklist())
                .personality(userEntity.getPersonality())
                .stateMessage(userEntity.getStateMessage())
                .mannerScore(userEntity.getMannerScore())
                .createdUserId(userEntity.getCreatedUserId())
                .updatedUserId(userEntity.getUpdatedUserId())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .isActive(userEntity.getIsActive())
                .build();
    }
    
    public UserDto fromDomainToDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .age(user.getAge())
                .phone(user.getPhone())
                .email(user.getEmail())
                .address(user.getAddress())
                .role(user.getRole())
                .blacklist(user.getBlacklist())
                .personality(user.getPersonality())
                .stateMessage(user.getStateMessage())
                .mannerScore(user.getMannerScore())
                .createdUserId(user.getCreatedUserId())
                .updatedUserId(user.getUpdatedUserId())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .isActive(user.getIsActive())
                .build();
    }
}