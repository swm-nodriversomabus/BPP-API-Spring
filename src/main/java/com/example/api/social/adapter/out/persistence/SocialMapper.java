package com.example.api.social.adapter.out.persistence;


import com.example.api.social.dto.AddSocialDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SocialMapper {

    @Mapping(source = "id", target = "googleId")
    @Mapping(target = "kakaoId", constant = "''")
    @Mapping(target = "instaId", constant = "''")
    @Mapping(target = "appleId", constant = "''")
    @Mapping(target = "naverId", constant = "''")
    SocialEntity toEntityGoogle(AddSocialDto addSocialDto);

    @Mapping(source = "id", target = "kakaoId")
    @Mapping(target = "googleId", constant = "''")
    @Mapping(target = "instaId", constant = "''")
    @Mapping(target = "appleId", constant = "''")
    @Mapping(target = "naverId", constant = "''")
    SocialEntity toEntityKakao(AddSocialDto addSocialDto);

    @Mapping(source = "id", target = "instaId")
    @Mapping(target = "kakaoId", constant = "''")
    @Mapping(target = "googleId", constant = "''")
    @Mapping(target = "appleId", constant = "''")
    @Mapping(target = "naverId", constant = "''")
    SocialEntity toEntityInsta(AddSocialDto addSocialDto);

    @Mapping(source = "id", target = "appleId")
    @Mapping(target = "kakaoId", constant = "''")
    @Mapping(target = "instaId", constant = "''")
    @Mapping(target = "googleId", constant = "''")
    @Mapping(target = "naverId", constant = "''")
    SocialEntity toEntityApple(AddSocialDto addSocialDto);

    @Mapping(source = "id", target = "naverId")
    @Mapping(target = "kakaoId", constant = "''")
    @Mapping(target = "instaId", constant = "''")
    @Mapping(target = "appleId", constant = "''")
    @Mapping(target = "googleId", constant = "''")
    SocialEntity toEntityNaver(AddSocialDto addSocialDto);
}
