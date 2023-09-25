package com.example.api.user.application.port.out;

import com.example.api.user.adapter.out.persistence.UserEntity;

import java.util.List;
import java.util.Optional;

public interface FindUserPort {
    List<UserEntity> getAllBy();
    Optional<UserEntity> getUserByUserId(Long userId);
    Optional<UserEntity> findUserSigned(Long socialId); // 로그인시 처리 - OAuth용
    Optional<UserEntity> findSocialUser(String id, String provider);
}