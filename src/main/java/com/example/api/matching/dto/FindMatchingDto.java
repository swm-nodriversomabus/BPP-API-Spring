package com.example.api.matching.dto;

import com.example.api.matching.type.MatchingTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindMatchingDto {
    @NotNull
    private Long matchingId;
    
    @NotNull
    private UUID writerId;
    
    @NotNull
    private UUID chatRoomId;
    
    @NotNull
    private MatchingTypeEnum type;
    
    @NotBlank
    private String title;
    
    @NotBlank
    private String place;

    @Size(min = -90, max = 90)
    private Double latitude;

    @Size(min = -180, max = 180)
    private Double longitude;
    
    @NotBlank
    private String content;
    
    @NotNull
    private LocalDateTime startDate;
    
    @NotNull
    private LocalDateTime endDate;
    
    @NotNull
    @Min(1)
    private Integer currentMember;
    
    @NotNull
    @Min(2)
    private Integer maxMember;
    
    @NotNull
    @Min(0)
    private Integer minusAge;
    
    @NotNull
    @Min(0)
    private Integer plusAge;
    
    @NotNull
    @Min(0)
    private Integer readCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    @NotNull
    private Boolean isActive;
}