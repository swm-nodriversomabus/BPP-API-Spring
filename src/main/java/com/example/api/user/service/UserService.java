package com.example.api.user.service;

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
import com.example.api.user.dto.CreaeUserDto;
import com.example.api.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

//    /**
//     * 유저가 회원가입 되어 있는지 여부 체크
//     * @return
//     */
//    public Optional<UserEntity> findUserSigned(String id, String provider){
//        return findSocialPort.findSocialUser(id, provider);
//    }
    
    @Override
    @Transactional
    public void createUser(CreaeUserDto userDto) {
        log.info("welcome");
        log.info(userDto.toString());
        SocialEntity social = findSocialPort.findSocialUser(userDto.getSocialEmail(), userDto.getProvider()).orElseThrow(()->new IllegalArgumentException("로그인 정보가 없습니다"));


        log.info(social.toString());
//        User user = saveUserPort.createUser(userMapper.toDomain(userDto));
//        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        return findUserPort.getAllBy().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<UserDto> getUserById(Long userId) {
        return findUserPort.getUserByUserId(userId)
                .map(userMapper::toDto);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long userId,UserDto userDto) {
        User user = saveUserPort.updateUser(userId, userMapper.toDomain(userDto));
        return userMapper.toDto(user);
    }
    
    @Override
    @Transactional
    public void deleteAll() {
        deleteUserPort.deleteAllBy();
    }
    
    @Override
    @Transactional
    public void deleteUser(Long userId) {
        deleteUserPort.deleteByUserId(userId);
    }

    public Optional<UserEntity> findSocialUser(String id, String provider) {
        return findUserPort.findSocialUser(id, provider);
    }


    public Optional<UserEntity> findUserSigned(Long id){
        return findUserPort.findUserSigned(id);
    }
}