package com.example.api.user.repository;

//import com.example.api.user.adapter.out.persistence.SocialEntity;
import com.example.api.user.adapter.out.persistence.UserEntity;
//import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> getAllBy();


    Optional<UserEntity> getUserEntityByUserId_UserId(Long userId);
//    Optional<UserEntity> getUserByUserId(Long userId);
    void deleteAllBy();

    void deleteByUserId_UserId(Long userId);


    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getUserEntityByUserId_KakaoId(String kakaoId);

    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getUserEntityByUserId_AppleId(String appleId);
    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getUserEntityByUserId_NaverId(String naverId);

    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getUserEntityByUserId_InstaId(String instaId);

    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getUserEntityByUserId_GoogleId(String googleId);


}