package com.example.api.user.dto;

import com.example.api.user.type.UserRoleEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthorityCheckDto {
    @NotNull
    private UUID userId;
    
    @NotNull
    private UserRoleEnum role;
    
    @NotNull
    private Boolean blacklist;
    
    @NotNull
    private Boolean isActive;
}