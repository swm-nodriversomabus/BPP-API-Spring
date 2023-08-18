package com.example.api.user.application.port.out;

public interface DeleteUserPort {
    void deleteAllBy();
    void deleteByUserId(Long userId);
}