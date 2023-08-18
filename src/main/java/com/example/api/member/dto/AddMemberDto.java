package com.example.api.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AddMemberDto {
    @NotNull
    private UUID chatroomId;

    @NotNull
    private List<Long> memberIds;
}