package com.example.api.user.repository;

import com.example.api.user.adapter.out.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> getAllBy();
    Optional<UserEntity> getUserByUserId(Long userId);
    void deleteAllBy();
    void deleteByUserId(Long userId);
}