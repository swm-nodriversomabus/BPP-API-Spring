package com.example.api.user.service;

import com.example.api.user.adapter.out.persistence.SocialEntity;
import com.example.api.user.adapter.out.persistence.UserEntity;
import com.example.api.user.application.port.out.FindSocialPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final FindSocialPort findSocialPort;

    /**
     * 유저가 회원가입 되어 있는지 여부 체크
     * @return
     */
    public Optional<UserEntity> findUserSigned(String id, String provider){
        return findSocialPort.findSocialUser(id, provider);
    }
}
