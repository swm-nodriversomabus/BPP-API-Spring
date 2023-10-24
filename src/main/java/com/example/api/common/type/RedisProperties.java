package com.example.api.common.type;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@Component
//@PropertySource(value = {"classpath:/application-${spring.profiles.active}.yml"})
//@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
}