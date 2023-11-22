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
public class SaveMatchingDto {
    private UUID chatRoomId;
    
    @NotNull
    private MatchingTypeEnum type;
    
    @NotBlank(message = "Title is empty")
    private String title;
    
    @NotBlank(message = "Place is empty")
    private String place;
    
//    @Size(min = -90, max = 90)
    private Double latitude;
    
//    @Size(min = -180, max = 180)
    private Double longitude;
    
    @NotNull
    private String content;
    
    @NotNull
    private LocalDateTime startDate;
    
    @NotNull
    private LocalDateTime endDate;
    
    @NotNull
    @Min(value = 2, message = "MaxMember must be at least 2")
    private Integer maxMember;
    
    @NotNull
    @Min(0)
    private Integer minusAge;
    
    @NotNull
    @Min(0)
    private Integer plusAge;
    
//    @Min(0)
    private Integer price;
    
//    @Min(0)
    private Integer pricePerOne;
    
    private String room;
    
    @NotNull
    @Min(0)
    private Integer readCount;
    
    @NotNull
    private Boolean isActive;
}