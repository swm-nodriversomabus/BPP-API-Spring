package com.example.api.fcm.repository;

import com.example.api.fcm.adapter.out.persistence.FcmTokenEntity;
import com.example.api.fcm.adapter.out.persistence.FcmTokenPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FcmTokenRepository extends JpaRepository<FcmTokenEntity, FcmTokenPK> {
    Optional<FcmTokenEntity> getByUserId(Long userId);
}