package com.example.api.blocklist.domain;

import com.example.api.user.domain.BlockUser;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockList {
    private UUID userId;
    private BlockUser blocklistUserId;
}
