package com.example.api.social.service;

import com.example.api.social.adapter.out.persistence.SocialEntity;
import com.example.api.social.application.port.out.SaveSocialPort;
import com.example.api.social.dto.AddSocialDto;
import com.example.api.social.application.port.out.FindSocialPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class SocialService {
    private final SaveSocialPort saveSocialPort;
    private final FindSocialPort findSocialPort;

    @Transactional
    public void saveSocialInfo(AddSocialDto addSocialDto) {
        saveSocialPort.saveSocial(addSocialDto);
    }

    public Optional<SocialEntity> findSocialInfo(String id, String provider) {
        return findSocialPort.findSocialUser(id, provider);
    }
}