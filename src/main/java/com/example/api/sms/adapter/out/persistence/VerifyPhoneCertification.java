package com.example.api.sms.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


/**
 * 핸드폰 인증 성공시 한시간 동안 유효하게 냅둠
 */
@Getter
@AllArgsConstructor
@RedisHash(value = "phone_check", timeToLive = 3600) // hash collection 명시
public class VerifyPhoneCertification implements Serializable {
    @Id
    String phone;
}
