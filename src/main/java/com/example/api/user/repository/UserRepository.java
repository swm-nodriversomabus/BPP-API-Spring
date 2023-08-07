package com.example.api.user.repository;

import com.example.api.user.adapter.out.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
    List<UserEntity> getAllBy();
    Optional<UserEntity> getUserByUserId(Long userId);
    void deleteAllBy();
    void deleteByUserId(Long userId);
}