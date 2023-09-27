package com.example.api.social.application.port.out;

import com.example.api.social.adapter.out.persistence.SocialEntity;

import java.util.Optional;

public interface FindSocialPort {
    Optional<SocialEntity> findSocialUser(String id, String provider);
}
