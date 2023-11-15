package com.example.api.blocklist.application.port.out;

import com.example.api.blocklist.domain.BlockList;

import java.util.UUID;

public interface AddBlockUserPort {
    void addBlockUser(BlockList blockList);
}
