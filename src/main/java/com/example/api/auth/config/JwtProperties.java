package com.example.api.auth.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@RefreshScope
//@PropertySource(value = {"classpath:/application-${spring.profiles.active}.yml"})
//@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    @Value("${jwt.secret}")
    private String secret;
}
