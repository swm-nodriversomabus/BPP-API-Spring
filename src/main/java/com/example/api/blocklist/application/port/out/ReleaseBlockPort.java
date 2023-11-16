package com.example.api.blocklist.application.port.out;

import java.util.UUID;

public interface ReleaseBlockPort {
    void deleteBlockUser(UUID userId, UUID blockUserId);
}