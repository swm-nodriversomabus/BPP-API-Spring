package com.example.api.matching.application.port.out;

public interface LikePort {
    int getLikeCount(Long matchingId);
    void toggleLike(Long userId, Long matchingId);
}