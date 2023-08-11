package com.example.api.user.application.port.out;

import com.example.api.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public interface SaveUserPort {
    User createUser(User user);
    User updateUser(User user);
}