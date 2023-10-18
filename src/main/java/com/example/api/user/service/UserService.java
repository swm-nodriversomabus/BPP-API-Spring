package com.example.api.user.service;

import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.api.user.dto.FindUserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public Optional<FindUserDto> getUserById(String userId) {
        try {
            return findUserPort.getByUserId(UUID.fromString(userId))
                    .map(userMapper::toDto);
        } catch (IllegalArgumentException e) {
            log.warn("Invalid userId: UUID transform failed.");
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public FindUserDto updateUser(String userId, UpdateUserDto userDto) {
        try {
            User user = saveUserPort.updateUser(UUID.fromString(userId), userMapper.toDomain(userDto));
            return userMapper.toDto(user);
        } catch (IllegalArgumentException e) {
            log.warn("Invalid userId: UUID transform failed.");
            return userMapper.toDto(userDto);
        }
    }
    
    @Override
    @Transactional
    public void deleteAll() {
        deleteUserPort.deleteAllBy();
    }
    
    @Override
    @Transactional
    public void deleteUser(String userId) {
        try {
            deleteUserPort.deleteByUserId(UUID.fromString(userId));
        } catch (IllegalArgumentException e) {
            log.warn("Invalid userId: UUID transform failed.");
        }
    }
    
    // Social
    
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return "Anonymous";
    }
    
    public User findSocialUser(String id, String provider) {
        return userMapper.toDomain(findUserPort.findSocialUser(id, provider).orElseThrow(IllegalStateException::new));
//        return SecurityUserDto.builder()
//                .userId(user.getUserId())
//                .naverId(user.getSocialId().getNaverId())
//                .appleId(user.getSocialId().getAppleId())
//                .kakaoId(user.getSocialId().getKakaoId())
//                .googleId(user.getSocialId().getGoogleId())
//                .instaId(user.getSocialId().getInstaId())
//                .role(user.getRole().getRole())
//                .build();
    }
    
    public Optional<UserEntity> findUserSigned(Long id) {
        return findUserPort.findUserSigned(id);
    }
}