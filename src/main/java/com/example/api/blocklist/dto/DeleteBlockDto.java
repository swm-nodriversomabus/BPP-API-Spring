package com.example.api.blocklist.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class DeleteBlockDto {
    @NotNull
    private UUID blocklistUserId;
}