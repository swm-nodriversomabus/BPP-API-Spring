package com.example.api.blocklist.repositorty;

import com.example.api.blocklist.adapter.out.persistence.BlockListEntity;
import com.example.api.blocklist.adapter.out.persistence.BlockListPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BlockListRepository extends JpaRepository<BlockListEntity, BlockListPK> {
    void deleteByUserIdAndBlocklistUserId_UserId(UUID userId, UUID blockListUserId);

    @EntityGraph(attributePaths = {"blocklistUserId"})
    Page<BlockListEntity> findAllByUserId(@Param("userId") UUID userId, Pageable pageable);
}
