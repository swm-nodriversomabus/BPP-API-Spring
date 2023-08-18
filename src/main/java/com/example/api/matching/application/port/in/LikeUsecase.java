package com.example.api.matching.application.port.in;

public interface LikeUsecase {
    int getLikeCount(Long matchingId);
    void toggleLike(Long userId, Long matchingId);
}