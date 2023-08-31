package com.example.api.matching.dto;

import com.example.api.matching.adapter.out.persistence.LikeEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LikeDto {
    @NotNull
    @Min(1)
    private Long userid;
    
    @NotNull
    @Min(1)
    private Long matchingId;
    
    private LocalDateTime createdAt;
    
    public LikeEntity toEntity() {
        return LikeEntity.builder()
                .userId(userid)
                .matchingId(matchingId)
                .build();
    }
}