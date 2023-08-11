package com.example.api.user.application.port.out;

import org.springframework.stereotype.Component;

@Component
public interface DeleteUserPort {
    void deleteAllBy();
    void deleteByUserId(Long userId);
}