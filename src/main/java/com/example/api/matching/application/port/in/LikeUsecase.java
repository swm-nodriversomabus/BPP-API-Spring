package com.example.api.matching.application.port.in;

import com.example.api.matching.dto.LikeDto;

public interface LikeUsecase {
    int getLikeCount(Long matchingId);
    void toggleLike(LikeDto likeDto);
}