package com.example.api.user.dto;

import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindUserInfoDto {
    @NotBlank
    private UUID userId;
    
    @NotBlank
    private String username;

    @NotNull
    private UserGenderEnum gender;

    @NotNull
    private Integer age;

    @NotBlank
    private String phone;

    @NotNull
    private UserRoleEnum role;

    @NotNull
    private Boolean blacklist;

    @NotNull
    private String stateMessage;

    @NotNull
    private Integer mannerScore;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;

    @NotNull
    private Boolean isActive;
}