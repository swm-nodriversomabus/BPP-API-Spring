package com.example.api.fcm.adapter.out.persistence;

import com.example.api.fcm.applocation.port.out.FindFcmTokenPort;
import com.example.api.fcm.applocation.port.out.SaveFcmTokenPort;
import com.example.api.fcm.domain.FcmToken;
import com.example.api.fcm.repository.FcmTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
@ComponentScan
public class FcmTokenPersistenceAdapter implements SaveFcmTokenPort, FindFcmTokenPort {
    private final FcmTokenMapperInterface fcmTokenMapper;
    private final FcmTokenRepository fcmTokenRepository;
    
    @Override
    public FcmToken saveFcmToken(FcmToken fcmToken) {
        FcmTokenEntity fcmTokenEntity = fcmTokenRepository.save(fcmTokenMapper.toEntity(fcmToken));
        return fcmTokenMapper.toDomain(fcmTokenEntity);
    }
    
    @Override
    public String findUserFcmToken(Long userId) {
        FcmTokenEntity fcmTokenEntity = fcmTokenRepository.getByUserId(userId)
                .orElseThrow(NoSuchElementException::new);
        return fcmTokenEntity.getFcmToken();
    }
}