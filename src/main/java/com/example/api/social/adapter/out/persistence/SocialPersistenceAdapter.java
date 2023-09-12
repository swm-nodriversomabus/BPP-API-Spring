package com.example.api.social.adapter.out.persistence;

import com.example.api.social.application.port.out.FindSocialPort;
import com.example.api.social.application.port.out.SaveSocialPort;
import com.example.api.social.dto.AddSocialDto;
import com.example.api.social.repository.SocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class SocialPersistenceAdapter implements SaveSocialPort, FindSocialPort {
    private final SocialRepository socialRepository;
    private final SocialMapper socialMapper;

    @Override
    public void saveSocial(AddSocialDto addSocialDto) {
        switch (addSocialDto.getProvider()){
            case "google" -> socialRepository.save(socialMapper.toEntityGoogle(addSocialDto));
            case "naver" -> socialRepository.save(socialMapper.toEntityNaver(addSocialDto));
            case "kakao" -> socialRepository.save(socialMapper.toEntityKakao(addSocialDto));
            case "apple" -> socialRepository.save(socialMapper.toEntityApple(addSocialDto));
            case "insta" -> socialRepository.save(socialMapper.toEntityInsta(addSocialDto));
            default -> throw new IllegalArgumentException();
        }
    }

    @Override
    public Optional<SocialEntity> findSocialUser(String id, String provider) {
        return switch (provider) {
            case "google" -> socialRepository.findSocialEntityByGoogleId(id);
            case "naver" -> socialRepository.findSocialEntityByNaverId(id);
            case "kakao" -> socialRepository.findSocialEntityByKakaoId(id);
            case "apple" -> socialRepository.findSocialEntityByAppleId(id);
            case "insta" -> socialRepository.findSocialEntityByInstaId(id);
            default -> Optional.empty();
        };
    }
}
