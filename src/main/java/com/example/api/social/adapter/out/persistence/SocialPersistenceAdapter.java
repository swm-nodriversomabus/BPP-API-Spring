package com.example.api.social.adapter.out.persistence;

import com.example.api.social.application.port.out.SaveSocialPort;
import com.example.api.social.dto.AddSocialDto;
import com.example.api.social.repository.SocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class SocialPersistenceAdapter implements SaveSocialPort {
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
        };
    }

}
