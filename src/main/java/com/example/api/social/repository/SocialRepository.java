package com.example.api.social.repository;

import com.example.api.social.adapter.out.persistence.SocialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialRepository extends JpaRepository<SocialEntity, Long> {
    // google id 기준 social entity 찾아오기
    Optional<SocialEntity> findSocialEntityByGoogleId(String id);
    // 애플 id 기준 social entity 찾아오기
    Optional<SocialEntity> findSocialEntityByAppleId(String id);
    // 카카오 id 기준 social entity 찾아오기
    Optional<SocialEntity> findSocialEntityByKakaoId(String id);
    // 네이버 id 기준 social entity 찾아오기
    Optional<SocialEntity> findSocialEntityByNaverId(String id);
    // 인스타 id 기준 social entity 찾아오기
    Optional<SocialEntity> findSocialEntityByInstaId(String id);


}

