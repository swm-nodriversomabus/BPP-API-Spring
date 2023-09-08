package com.example.api.user.adapter.out.persistence;

import com.example.api.user.application.port.out.DeleteUserPort;
import com.example.api.user.application.port.out.FindUserPort;
import com.example.api.user.application.port.out.SaveUserPort;
import com.example.api.user.domain.User;
import com.example.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ComponentScan
public class UserPersistenceAdapter implements SaveUserPort, FindUserPort, DeleteUserPort {
    private final UserMapperInterface userMapper;
    private final UserRepository userRepository;
    
    @Override
    public User createUser(User user) {
        UserEntity userData = userRepository.save(userMapper.toEntity(user));
        return userMapper.toDomain(userData);
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
}