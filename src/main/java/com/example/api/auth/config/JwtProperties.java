package com.example.api.auth.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = {"classpath:/application-${spring.profiles.active}.yml"})
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secret;
}
