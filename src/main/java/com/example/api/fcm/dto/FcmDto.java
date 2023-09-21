package com.example.api.fcm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FcmDto {
    @NotNull
    private Long userId;
    
    private String title;
    
    private String body;
}