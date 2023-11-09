package com.example.api.matching.application.port.out;

import com.example.api.matching.adapter.out.persistence.LikeEntity;

public interface LikePort {
    int getLikeCount(Long matchingId);
    void toggleLike(LikeEntity likeEntity);
}