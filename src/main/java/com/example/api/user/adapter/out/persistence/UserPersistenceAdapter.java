package com.example.api.user.adapter.out.persistence;

import com.example.api.user.application.port.out.DeleteUserPort;
import com.example.api.user.application.port.out.FindUserPort;
import com.example.api.user.application.port.out.SaveUserPort;
import com.example.api.user.domain.CreateUser;
import com.example.api.user.domain.User;
import com.example.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Slf4j
@ComponentScan
public class UserPersistenceAdapter implements SaveUserPort, FindUserPort, DeleteUserPort {
    private final UserMapperInterface userMapper;
    private final UserRepository userRepository;
    
    @Override
    public void createUser(CreateUser user) {
        UserEntity userData = userRepository.save(userMapper.toEntity(user));
    }
    
    @Override
    public List<UserEntity> getAllBy() {
        return userRepository.getAllBy();
    }
    
    @Override
    public Optional<UserEntity> getUserByUserId(Long userId) {
        return userRepository.getByUserId(userId);
    }
    
    @Override
    public User updateUser(Long userId, User user) {
        user.setUserId(userId);
        UserEntity userData = userRepository.save(userMapper.toEntity(user));
        return userMapper.toDomain(userData);
    }
    
    @Override
    public void deleteAllBy() {
        userRepository.deleteAllBy();
    }
    
    @Override
    public void deleteByUserId(Long userId) {
        userRepository.deleteByUserId(userId);
    }
    
    // Social

    @Override
    public Optional<UserEntity> findSocialUser(String id, String provider) {
        return switch (provider){
            case "naver" -> userRepository.getBySocialId_NaverId(id);
            case "kakao" -> userRepository.getBySocialId_KakaoId(id);
            case "google" -> userRepository.getBySocialId_GoogleId(id);
            case "apple" -> userRepository.getBySocialId_AppleId(id);
            case "insta" -> userRepository.getBySocialId_InstaId(id);
            default -> Optional.empty();
        };
    }

    @Override
    public Optional<UserEntity> findUserSigned(Long socialId) {
        return userRepository.getBySocialId_SocialId(socialId);
    }
}