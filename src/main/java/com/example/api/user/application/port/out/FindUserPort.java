package com.example.api.user.application.port.out;

import com.example.api.user.adapter.out.persistence.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface FindUserPort {
    List<UserEntity> getAllBy();
    Optional<UserEntity> getUserByUserId(Long userId);
}