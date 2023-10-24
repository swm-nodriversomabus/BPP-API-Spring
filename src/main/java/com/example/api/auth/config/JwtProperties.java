package com.example.api.auth.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtProperties {
    @Value("${jwt.secret}")
    private String secret;
}