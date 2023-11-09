package com.example.api.fcm.adapter.out.persistence;

import com.example.api.fcm.domain.FcmToken;
import com.example.api.fcm.dto.FcmTokenDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FcmTokenMapperInterface {
    FcmToken toDomain(FcmTokenDto fcmTokenDto);
    FcmTokenEntity toEntity(FcmToken fcmToken);
    FcmToken toDomain(FcmTokenEntity fcmTokenEntity);
    FcmTokenDto toDto(FcmToken fcmToken);
}