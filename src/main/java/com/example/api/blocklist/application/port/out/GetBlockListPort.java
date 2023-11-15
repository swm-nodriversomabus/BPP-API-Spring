package com.example.api.blocklist.application.port.out;

import com.example.api.blocklist.domain.BlockList;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface GetBlockListPort {
    List<BlockList> getBlockUserList(UUID userId, Pageable pageable);
}
