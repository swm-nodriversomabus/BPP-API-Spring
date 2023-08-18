package com.example.api.user.adapter.out.persistence;


import com.example.api.user.domain.ChatUser;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


// 추후 매핑 이걸로 변경
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapperInterface {
    ChatUser toDomain(UserEntity userEntity);
}
