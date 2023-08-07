package com.example.api.user.repository;

import com.example.api.user.adapter.out.persistence.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    EntityManager em;
    
    @Override
    public void createUser(UserEntity userEntity) {
            em.persist(userEntity);
    }
    
    @Override
    public void updateUser(Long userId, UserEntity userEntity) {
        UserEntity user = em.find(UserEntity.class, userId);
        user.setUsername(userEntity.getUsername());
        user.setNickname(userEntity.getNickname());
        user.setGender(userEntity.getGender());
        user.setAge(userEntity.getAge());
        user.setPhone(userEntity.getPhone());
        user.setEmail(userEntity.getEmail());
        user.setAddress(userEntity.getAddress());
        user.setRole(userEntity.getRole());
        user.setBlacklist(userEntity.getBlacklist());
        user.setPersonality(userEntity.getPersonality());
        user.setStateMessage(userEntity.getStateMessage());
        user.setMannerScore(userEntity.getMannerScore());
        user.setUpdatedUserId(userEntity.getUpdatedUserId());
        user.setIsActive(userEntity.getIsActive());
    }
}