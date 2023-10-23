package com.example.api.matching.dto;

import com.example.api.common.type.ApplicationStateEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaveMatchingApplicationDto {
    @NotNull
    @Min(1)
    private Long matchingId;

    @NotNull
    private ApplicationStateEnum state;

    @NotNull
    private Boolean isActive;
}