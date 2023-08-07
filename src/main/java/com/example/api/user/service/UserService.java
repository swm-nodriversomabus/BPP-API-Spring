package com.example.api.user.service;

import com.example.api.user.adapter.out.persistence.UserEntity;
import com.example.api.user.repository.UserRepository;
import com.example.api.user.application.port.in.UserUsecase;
import com.example.api.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserUsecase {
    
    private final UserRepository userRepository;
    
    @Override
    @Transactional
    public void createUser(UserDto userDto) {
        userRepository.createUser(userDto.toEntity());
    }
    
    @Override
    public List<UserDto> getAll() {
        return userRepository.getAllBy().stream()
                .map(UserEntity::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<UserDto> getUserById(Long userId) {
        return userRepository.getUserByUserId(userId)
                .map(UserEntity::toDto);
    }
    
    @Override
    @Transactional
    public void updateUser(Long userId, UserDto userDto) {
        //userRepository.updateUser(userId, userDto);
    }
    
    @Override
    @Transactional
    public void deleteAll() {
        userRepository.deleteAllBy();
    }
    
    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteByUserId(userId);
    }
}