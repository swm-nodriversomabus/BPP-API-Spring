package com.example.api.user.repository;

import com.example.api.user.adapter.out.persistence.SocialEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocialRepository extends JpaRepository<SocialEntity, Long> {
    @EntityGraph(attributePaths = {"userId"})
    Optional<SocialEntity> getByKakaoId(String kakaoId);

    @EntityGraph(attributePaths = {"userId"})
    Optional<SocialEntity> getByAppleId(String appleId);

    @EntityGraph(attributePaths = {"userId"})
    Optional<SocialEntity> getByNaverId(String naverId);

    @EntityGraph(attributePaths = {"userId"})
    Optional<SocialEntity> getByInstaId(String instaId);

    @EntityGraph(attributePaths = {"userId"})
    Optional<SocialEntity> getByGoogleId(String googleId);
}
