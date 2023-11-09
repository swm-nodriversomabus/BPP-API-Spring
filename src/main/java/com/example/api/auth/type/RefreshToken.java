package com.example.api.auth.type;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;


@Getter
@AllArgsConstructor
@RedisHash(value = "jwtToken", timeToLive = 60 * 60 * 24 * 14) // hash collection 명시
public class RefreshToken implements Serializable { // 직렬화, 역 직렬화 지원
    @Id // hash의 key값
    private String id;

    @Indexed // 이 애노테이션 사용시 jpa의 findby처럼 사용 가능
    private String accessToken; // refresh는 access 기반 찾음

    private String refreshToken;

    public void updateAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
