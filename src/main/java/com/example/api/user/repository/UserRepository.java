package com.example.api.user.repository;

import com.example.api.user.adapter.out.persistence.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> getAllBy();
    Optional<UserEntity> getByUserId(Long userId);
    void deleteAllBy();
    void deleteByUserId(Long userId);
    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getBySocialId_NaverId(String naverId);
    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getBySocialId_KakaoId(String kakaoId);
    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getBySocialId_GoogleId(String googleId);
    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getBySocialId_AppleId(String appleId);
    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getBySocialId_InstaId(String instaId);
    Optional<UserEntity> getBySocialId_SocialId(Long id);
}