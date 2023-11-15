package com.example.api.blocklist.application.port.in;

import com.example.api.blocklist.dto.AddBlockDto;
import com.example.api.blocklist.dto.DeleteBlockDto;

import java.util.UUID;

public interface ReleaseBlockUsecase {
    void releaseBlockUser(DeleteBlockDto addBlockDto, UUID userId);
}
