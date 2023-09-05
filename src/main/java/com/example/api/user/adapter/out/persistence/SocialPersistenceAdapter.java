package com.example.api.user.adapter.out.persistence;

import com.example.api.user.application.port.out.FindSocialPort;
import com.example.api.user.repository.SocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@RequiredArgsConstructor
@Component
public class SocialPersistenceAdapter implements FindSocialPort {
    private final SocialRepository socialRepository;

    @Override
    public Optional<SocialEntity> findSocialUser(String id, String provider) {
        return switch (provider){
            case "google" -> socialRepository.getByGoogleId(id);
            case "naver" -> socialRepository.getByNaverId(id);
            case "kakao" -> socialRepository.getByKakaoId(id);
            case "apple" -> socialRepository.getByAppleId(id);
            case "insta" -> socialRepository.getByInstaId(id);
            default -> Optional.empty();
        };
//        return Optional.empty();
    }
}
