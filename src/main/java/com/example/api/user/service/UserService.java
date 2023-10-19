package com.example.api.user.service;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.common.utils.AuthenticationUtils;
import com.example.api.common.utils.CustomBase64Utils;
import com.example.api.sms.application.port.out.CheckVerifiedPhonePort;
import com.example.api.social.adapter.out.persistence.SocialEntity;
import com.example.api.user.adapter.out.persistence.UserEntity;
import com.example.api.user.adapter.out.persistence.UserMapperInterface;
import com.example.api.user.application.port.in.DeleteUserUsecase;
import com.example.api.user.application.port.in.FindUserUsecase;
import com.example.api.user.application.port.out.DeleteUserPort;
import com.example.api.social.application.port.out.FindSocialPort;
import com.example.api.user.application.port.out.FindUserPort;
import com.example.api.user.application.port.out.SaveUserPort;
import com.example.api.user.domain.User;
import com.example.api.user.application.port.in.SaveUserUsecase;
import com.example.api.user.dto.CreateUserDto;
import com.example.api.user.dto.UpdateUserDto;
import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.api.user.dto.FindUserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements SaveUserUsecase, FindUserUsecase, DeleteUserUsecase {
    private final UserMapperInterface userMapper;
    private final SaveUserPort saveUserPort;
    private final FindUserPort findUserPort;
    private final DeleteUserPort deleteUserPort;
    private final FindSocialPort findSocialPort;
    private final CheckVerifiedPhonePort checkVerifiedPhonePort;

    public FindUserDto getDefaultUser() {
        return FindUserDto.builder()
                .username("Anonymous")
                .gender(UserGenderEnum.None)
                .age(30)
                .phone("010-0000-0000")
                .role(UserRoleEnum.User)
                .blacklist(false)
                .stateMessage("")
                .mannerScore(60)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isActive(false)
                .build();
    }
    
    @Override
    @Transactional
    public void createUser(CreateUserDto userDto) {
        checkVerifiedPhonePort.findCheckedPhone(userDto.getPhone()); // 인증여부 검증 추가
        SocialEntity social = findSocialPort.findSocialUser(CustomBase64Utils.getBase64DecodeString(userDto.getSocialEmail()), CustomBase64Utils.getBase64DecodeString(userDto.getProvider())).orElseThrow(()->new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE));
        userDto.setSocialId(social.getSocialId());
        saveUserPort.createUser(userMapper.toDomain(userDto));
    }

    @Override
    public List<FindUserDto> getAll() {
        return findUserPort.getAllBy().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<FindUserDto> getUserById(String userId) {
        try {
            return findUserPort.getByUserId(UUID.fromString(userId))
                    .map(userMapper::toDto);
        } catch (IllegalArgumentException e) {
            log.warn("UserService::getUserById: UUID transform failed.");
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public FindUserDto updateUser(UpdateUserDto userDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("UserService::updateUser: Authentication is needed.");
            return userMapper.toDto(userDto);
        }
        User user = saveUserPort.updateUser(securityUser.getUserId(), userMapper.toDomain(userDto));
        return userMapper.toDto(user);
    }
    
    @Override
    @Transactional
    public void deleteAll() {
        deleteUserPort.deleteAllBy();
    }
    
    @Override
    @Transactional
    public void deleteUser() {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        try {
            if (securityUser == null) {
                log.error("UserService::deleteUser: Authentication is needed.");
                throw new Exception();
            }
            deleteUserPort.deleteByUserId(securityUser.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Social
    
    public SecurityUser findSocialUser(String id, String provider) {
        User user = userMapper.toDomain(findUserPort.findSocialUser(id, provider).orElseThrow(IllegalStateException::new));

        return SecurityUser.builder()
                .userId(user.getUserId())
                .naverId(user.getSocialId().getNaverId())
                .appleId(user.getSocialId().getAppleId())
                .kakaoId(user.getSocialId().getKakaoId())
                .googleId(user.getSocialId().getGoogleId())
                .instaId(user.getSocialId().getInstaId())
                .role(user.getRole().getRole())
                .build();
    }
    
    public Optional<UserEntity> findUserSigned(Long id) {
        return findUserPort.findUserSigned(id);
    }
}