package com.example.api.fcm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FcmDto {
    @NotNull
    private UUID userId;
    
    private Long matchingId;
    
    private String title;
    
    private String body;
}