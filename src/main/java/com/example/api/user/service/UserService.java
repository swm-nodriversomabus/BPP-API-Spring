package com.example.api.user.service;

import com.example.api.user.adapter.out.persistence.UserEntity;
import com.example.api.user.adapter.out.persistence.UserMapperInterface;
import com.example.api.user.application.port.in.DeleteUserUsecase;
import com.example.api.user.application.port.in.FindUserUsecase;
import com.example.api.user.application.port.out.DeleteUserPort;
import com.example.api.user.application.port.out.FindUserPort;
import com.example.api.user.application.port.out.SaveUserPort;
import com.example.api.user.domain.User;
import com.example.api.user.application.port.in.SaveUserUsecase;
import com.example.api.user.dto.FindUserDto;
import com.example.api.user.dto.SaveUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements SaveUserUsecase, FindUserUsecase, DeleteUserUsecase {
    private final UserMapperInterface userMapper;
    private final SaveUserPort saveUserPort;
    private final FindUserPort findUserPort;
    private final DeleteUserPort deleteUserPort;
    
    @Override
    @Transactional
    public FindUserDto createUser(SaveUserDto userDto) {
        User user = saveUserPort.createUser(userMapper.toDomain(userDto));
        return userMapper.toDto(user);
    }
    
    @Override
    public List<FindUserDto> getAll() {
        return findUserPort.getAllBy().stream()
                .map(UserEntity::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<FindUserDto> getUserById(Long userId) {
        return findUserPort.getUserByUserId(userId)
                .map(UserEntity::toDto);
    }

    @Override
    @Transactional
    public FindUserDto updateUser(Long userId, SaveUserDto userDto) {
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
}