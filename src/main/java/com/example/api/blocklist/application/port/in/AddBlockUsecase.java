package com.example.api.blocklist.application.port.in;

import com.example.api.blocklist.dto.AddBlockDto;

import java.util.UUID;

public interface AddBlockUsecase {
    void addBlockUser(AddBlockDto addBlockDto, UUID userId);
}