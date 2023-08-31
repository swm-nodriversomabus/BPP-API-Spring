package com.example.api.friend.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class FriendPK implements Serializable {
    private Long userId;
    private Long friendId;
}