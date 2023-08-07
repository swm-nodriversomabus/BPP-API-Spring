package com.example.api.user.repository;

import com.example.api.user.adapter.out.persistence.UserEntity;

public interface UserRepositoryCustom {
    void createUser(UserEntity userEntity);
    void updateUser(Long userId, UserEntity userEntity);
}