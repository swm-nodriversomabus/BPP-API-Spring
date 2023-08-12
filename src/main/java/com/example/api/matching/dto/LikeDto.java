package com.example.api.matching.dto;

import com.example.api.matching.adapter.out.persistence.LikeEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LikeDto {
    @NotEmpty
    private Long userid;
    
    @NotEmpty
    private Long matchingId;
    
    private LocalDateTime createdAt;
    
    public LikeEntity toEntity() {
        return LikeEntity.builder()
                .userId(userid)
                .matchingId(matchingId)
                .build();
    }
}