package com.example.api.member.adapter.out.persistence;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class MemberPK implements Serializable {
    private UUID chatroomId;
    private Long userId;
}
