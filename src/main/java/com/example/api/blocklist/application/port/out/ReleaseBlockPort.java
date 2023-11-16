package com.example.api.blocklist.application.port.out;

import com.example.api.blocklist.domain.BlockList;

import java.util.UUID;

public interface ReleaseBlockPort {
    void deleteBlockUser(UUID userId, UUID blockUserId);
}

