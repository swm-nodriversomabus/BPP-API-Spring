package com.example.api.user.adapter.out.persistence;

import com.example.api.user.application.port.out.DeleteUserPort;
import com.example.api.user.application.port.out.FindUserPort;
import com.example.api.user.application.port.out.SaveUserPort;
import com.example.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements SaveUserPort, FindUserPort, DeleteUserPort {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    
    @Override
    public User createUser(User user) {
        UserEntity userData = userRepository.save(userMapper.fromDomainToEntity(user));
        return userMapper.fromEntityToDomain(userData);
    }
    
    @Override
    public List<UserEntity> getAllBy() {
        return userRepository.getAllBy();
    }
    
    @Override
    public Optional<UserEntity> getUserByUserId(Long userId) {
        return userRepository.getUserByUserId(userId);
    }
    
    @Override
    public User updateUser(User user) {
        UserEntity userData = userRepository.save(userMapper.fromDomainToEntity(user));
        return userMapper.fromEntityToDomain(userData);
    }
    
    @Override
    public void deleteAllBy() {
        userRepository.deleteAllBy();
    }
    
    @Override
    public void deleteByUserId(Long userId) {
        userRepository.deleteByUserId(userId);
    }
}