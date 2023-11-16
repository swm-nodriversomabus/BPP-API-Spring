package com.example.api.blocklist.application.port.in;

import com.example.api.user.domain.BlockUser;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface GetListUsecase {
    List<BlockUser> getBlockList(Pageable pageable, UUID userId);
}