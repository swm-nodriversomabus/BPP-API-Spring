package com.example.api.blocklist.application.port.out;

import com.example.api.blocklist.adapter.out.persistence.BlockListEntity;
import com.example.api.blocklist.domain.BlockList;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetBlockListPort {
    Optional<BlockListEntity> getBlockUser(UUID userId, UUID blockedUserId);
    List<BlockList> getBlockUserList(UUID userId, Pageable pageable);
}