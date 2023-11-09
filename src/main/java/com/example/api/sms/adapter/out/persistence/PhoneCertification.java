package com.example.api.sms.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;

/**
 * 핸드폰 인증에 사용되는 클래스
 */
@Getter
@AllArgsConstructor
@RedisHash(value = "phone_check", timeToLive = 360) // hash collection 명시
public class PhoneCertification implements Serializable {
    @Id
    private String phone;
    private String code;
}