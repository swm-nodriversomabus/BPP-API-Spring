package com.example.api.matching.dto;

import com.example.api.matching.type.MatchingTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaveMatchingDto {
    @NotNull
    private MatchingTypeEnum type;
    
    @NotBlank
    private String title;
    
    @NotBlank
    private String place;
    
    @NotBlank
    private String content;
    
    @NotNull
    private LocalDateTime startDate;
    
    @NotNull
    private LocalDateTime endDate;
    
    @NotNull
    @Min(1)
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
    
    @NotNull
    private Boolean isActive;
}