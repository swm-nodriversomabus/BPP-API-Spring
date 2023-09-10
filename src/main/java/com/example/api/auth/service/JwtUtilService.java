package com.example.api.auth.service;


import com.example.api.auth.config.JwtProperties;
import com.example.api.auth.utils.GeneratedToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtUtilService {
    private final JwtProperties jwtProperties;
    private final RefreshTokenService refreshTokenService;
    private Key secretKey;

    @PostConstruct
    protected void init() {
        log.info("asdasdasd");
        log.info(jwtProperties.getSecret());
        String base64Encoded = Base64.getEncoder().encodeToString(jwtProperties.getSecret().getBytes());
        secretKey = Keys.hmacShaKeyFor(base64Encoded.getBytes());
    }

    public GeneratedToken generatedToken(String id, String role, String provider) {
        // token
        String refreshToken = genereateRefreshToken(id, role, provider);
        String accessToken = generateAccessToken(id, role, provider);

        // 저장
        refreshTokenService.saveToken(id, accessToken, refreshToken);
        return new GeneratedToken(accessToken, refreshToken);
    }

    public String genereateRefreshToken(String id, String role, String provider) {
        long tokenPeriod = 1000L * 60L * 60L * 24L * 14; // 2주 유효성

        // 새 클레임 객체를 생성하고, 이메일과 역할을 셋팅
        return getToken(id, role, provider, tokenPeriod);


    }

    public String generateAccessToken(String id, String role, String provider) {
        long tokenPeriod = 1000L * 60L * 60L; // 1시간 유효성
        return getToken(id, role, provider, tokenPeriod);
    }

    private String getToken(String id, String role, String provider, long tokenPeriod) {
        Claims claims = Jwts.claims().setSubject(id);
        claims.put("role", role);
        claims.put("provider", provider);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + tokenPeriod))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build().parseClaimsJws(token);
            return claimsJws.getBody().getExpiration().after(new Date()); // 만료 시간 유혀성 검사
        } catch (Exception e) {
            return false;
        }
    }

    public String getId(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String getRole(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("role", String.class);
    }

    public String getProvider(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("provider", String.class);

    }
}
