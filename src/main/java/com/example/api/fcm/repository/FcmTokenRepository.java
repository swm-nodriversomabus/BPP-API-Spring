package com.example.api.fcm.repository;

import com.example.api.fcm.adapter.out.persistence.FcmTokenEntity;
import com.example.api.fcm.adapter.out.persistence.FcmTokenPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface FcmTokenRepository extends JpaRepository<FcmTokenEntity, FcmTokenPK> {
    Optional<FcmTokenEntity> getByUserId(Long userId);
}