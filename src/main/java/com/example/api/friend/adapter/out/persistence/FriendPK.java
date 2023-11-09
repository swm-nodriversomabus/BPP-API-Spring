package com.example.api.friend.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class FriendPK implements Serializable {
    private UUID userId;
    private UUID friendId;
}