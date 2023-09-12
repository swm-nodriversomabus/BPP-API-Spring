package com.example.api.social.service;

import com.example.api.user.adapter.out.persistence.UserEntity;
import com.example.api.social.application.port.out.FindSocialPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocialService {
    private final FindSocialPort findSocialPort;
    public Optional<UserEntity> findUserSigned(String id, String provider){
        return findSocialPort.findSocialUser(id, provider);
    }
}
