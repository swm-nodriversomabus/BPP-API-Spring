package com.example.api.user.repository;

//import com.example.api.user.adapter.out.persistence.SocialEntity;
import com.example.api.user.adapter.out.persistence.UserEntity;
//import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> getAllBy();
    Optional<UserEntity> getUserByUserId(Long userId);
    void deleteAllBy();
    void deleteByUserId(Long userId);

    Optional<UserEntity> getByKakaoId(String kakaoId);

    Optional<UserEntity> getByAppleId(String appleId);

    Optional<UserEntity> getByNaverId(String naverId);

    Optional<UserEntity> getByInstaId(String instaId);

    Optional<UserEntity> getByGoogleId(String googleId);


}