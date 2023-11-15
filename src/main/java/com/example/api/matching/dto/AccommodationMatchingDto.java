package com.example.api.matching.dto;

import com.example.api.matching.type.MatchingTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class AccommodationMatchingDto {
    private Long matchingId;

    @NotNull
    private UUID writerId;

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

    @Min(0)
    private Integer price;

    @Min(0)
    private Integer pricePerOne;

    private String room;

    @NotNull
    @Min(0)
    private Integer readCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotNull
    private Boolean isActive;
}