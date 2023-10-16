package com.example.api.fcm.service;

import com.example.api.fcm.adapter.out.persistence.FcmTokenMapperInterface;
import com.example.api.fcm.applocation.port.out.FindFcmTokenPort;
import com.example.api.fcm.applocation.port.out.SaveFcmTokenPort;
import com.example.api.fcm.domain.FcmToken;
import com.example.api.fcm.dto.FcmTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FcmTokenService {
    private final FcmTokenMapperInterface fcmTokenMapper;
    private final SaveFcmTokenPort saveFcmTokenPort;
    private final FindFcmTokenPort findFcmTokenPort;
    
    public FcmTokenDto saveFcmToken(FcmTokenDto fcmTokenDto) {
        FcmToken fcmToken = saveFcmTokenPort.saveFcmToken(fcmTokenMapper.toDomain(fcmTokenDto));
        return fcmTokenMapper.toDto(fcmToken);
    }
    
    public String findUserFcmToken(String userId) {
        return findFcmTokenPort.findUserFcmToken(UUID.fromString(userId));
    }
}