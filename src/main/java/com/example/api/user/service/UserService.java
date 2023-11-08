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
import com.example.api.user.dto.UserAuthorityCheckDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.api.user.dto.FindUserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public FindUserDto getUser(UUID userId) {
        Optional<UserEntity> userEntity = findUserPort.getByUserId(userId);
        if (userEntity.isEmpty()) {
            log.error("UserService::getUser: No such user");
            throw new CustomException(ErrorCodeEnum.USER_NOT_FOUND);
        }
        return userMapper.toDto(userEntity.get());
    }

    @Override
    public UserAuthorityCheckDto getAuthorityUser(UUID userId) {
        Optional<UserEntity> userEntity = findUserPort.getByUserId(userId);
        if (userEntity.isEmpty()) {
            log.error("UserService::getAuthorityUser: No such user");
            throw new CustomException(ErrorCodeEnum.USER_NOT_FOUND);
        }
        return userMapper.toAuthorityDto(userEntity.get());
    }

    @Override
    @Transactional
    public FindUserDto updateUser(UpdateUserDto userDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("UserService::updateUser: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        Optional<UserEntity> userEntity = findUserPort.getByUserId(securityUser.getUserId());
        if (userEntity.isEmpty()) {
            log.error("UserService::updateUser: No such user");
            throw new CustomException(ErrorCodeEnum.USER_NOT_FOUND);
        }
        User userdata = userMapper.toDomain(userEntity.get());
        User user = userMapper.toDomain(userDto);
        user.setUserId(securityUser.getUserId());
        user.setSocialId(userdata.getSocialId());
        return userMapper.toDto(saveUserPort.updateUser(securityUser.getUserId(), user));
    }
    
    @Override
    @Transactional
    public void deleteAll() {
        deleteUserPort.deleteAllBy();
    }
    
    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        deleteUserPort.deleteByUserId(userId);
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