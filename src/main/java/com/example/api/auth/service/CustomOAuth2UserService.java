package com.example.api.auth.service;

import com.example.api.auth.domain.OAuth2Attribute;
import com.example.api.social.adapter.out.persistence.SocialEntity;
import com.example.api.social.service.SocialService;
import com.example.api.user.adapter.out.persistence.UserEntity;
import com.example.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final SocialService socialService;
    private final UserService userService;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 기본 OAUth2UserService 생성
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        // OAuth2UserService를 사용하여 OAuth2User 정보 가져오기
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        // 클라이언트 등록 ID(google, kakao ...) 와 사용자 이름 속성 가져온다
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // OAuth2UserService를 사용하여 가져온 OAuth2User 정보로 OAuth2Attribute 객체를 만든다.
        OAuth2Attribute oAuth2Attribute = OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // OAuth2Attribute의 속성값을 Map으로 반환
        Map<String, Object> userAttribute = oAuth2Attribute.convertToMap();

        // 사용자의 이메일 정보를 가져온다
        String email = (String) userAttribute.get("email");
        // 이메일로 가입되어 있는지를 체크한다
        // TODO 타입은 수정 예정

        Optional<SocialEntity> findSocial = socialService.findSocialInfo(email, registrationId);

        if (findSocial.isEmpty()){
            userAttribute.put("exist", 1);
            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("NO_USER")),
                    userAttribute, "email"
            );
        }

        Optional<UserEntity> findUser = userService.findUserSigned(findSocial.get().getSocialId()); // user를 소셜 아이디 기준으로 찾자

        if (findUser.isEmpty()) { // 이러면 소셜은 누르고, 회원 가입은 안한 경우이다
            userAttribute.put("exist", 2);
            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("NO_USER")),
                    userAttribute, "email"
            );
        }
        userAttribute.put("exist", 3); // 소셜 로그인, 회원가입 다된겨우
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_".concat(findUser.get().getRole().getRole()))),
                userAttribute, "email"
        );

    }
}
