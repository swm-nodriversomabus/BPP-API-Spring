package com.example.api.blocklist.adapter.out.persistence;

import com.example.api.user.adapter.out.persistence.UserEntity;

import java.io.Serializable;
import java.util.UUID;

public class BlockListPK implements Serializable {
    private UUID userId;
    private UserEntity blocklistUserId;
}
