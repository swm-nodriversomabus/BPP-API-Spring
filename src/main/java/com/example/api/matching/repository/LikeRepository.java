package com.example.api.matching.repository;

import com.example.api.matching.adapter.out.persistence.LikeEntity;
import com.example.api.matching.adapter.out.persistence.LikePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, LikePK> {
    int countByMatchingId(Long matchingId);
}