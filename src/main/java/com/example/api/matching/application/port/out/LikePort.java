package com.example.api.matching.application.port.out;

import org.springframework.stereotype.Component;

@Component
public interface LikePort {
    int getLikeCount(Long matchingId);
    void toggleLike(Long userId, Long matchingId);
}