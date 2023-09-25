package com.example.api.auth.repository;


import com.example.api.auth.type.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    // accessToken 기반 refreshToekn 가져오기
    Optional<RefreshToken> findByAccessToken(String accessToken);
}
