package com.example.api.user.repository;

//import com.example.api.user.adapter.out.persistence.SocialEntity;
import com.example.api.user.adapter.out.persistence.UserEntity;
//import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> getAllBy();


    Optional<UserEntity> getUserEntityByUserId(Long userId);
//    Optional<UserEntity> getUserByUserId(Long userId);
    void deleteAllBy();

    void deleteByUserId(Long userId);


    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getUserEntityBySocialId_KakaoId(String kakaoId);

    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getUserEntityBySocialId_AppleId(String appleId);

    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getUserEntityBySocialId_NaverId(String naverId);

    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getUserEntityBySocialId_InstaId(String instaId);

    @EntityGraph(attributePaths = {"userId"})
    Optional<UserEntity> getUserEntityBySocialId_GoogleId(String googleId);

    Optional<UserEntity> getUserEntityBySocialId(Long id);

}