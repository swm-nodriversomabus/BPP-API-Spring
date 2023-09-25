package com.example.api.friend.adapter.out.persistence;

import com.example.api.friend.domain.Friend;
import com.example.api.friend.dto.FriendDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FriendMapperInterface {
    Friend toDomain(FriendDto friendDto);
    FriendEntity toEntity(Friend friend);
    Friend toDomain(FriendEntity friendEntity);
    FriendDto toDto(Friend friend);
}