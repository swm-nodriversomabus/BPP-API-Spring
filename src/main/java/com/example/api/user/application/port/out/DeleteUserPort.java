package com.example.api.user.application.port.out;

import java.util.UUID;

public interface DeleteUserPort {
    void deleteAllBy();
    void deleteByUserId(UUID userId);
}