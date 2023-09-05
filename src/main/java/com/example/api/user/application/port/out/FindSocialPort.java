package com.example.api.user.application.port.out;

import com.example.api.user.adapter.out.persistence.SocialEntity;

import java.util.Optional;

public interface FindSocialPort {
    Optional<SocialEntity> findSocialUser(String id, String provider);
}
