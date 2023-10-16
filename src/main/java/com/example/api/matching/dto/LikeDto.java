package com.example.api.matching.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LikeDto {
    @NotNull
    private UUID userid;
    
    @NotNull
    @Min(1)
    private Long matchingId;
    
    private LocalDateTime createdAt;
}