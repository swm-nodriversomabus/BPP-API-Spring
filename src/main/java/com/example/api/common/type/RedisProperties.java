package com.example.api.common.type;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = {"classpath:/application-${spring.profiles.active}.yml"})
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
    private String host;
    private int port;
    private String password;
}
