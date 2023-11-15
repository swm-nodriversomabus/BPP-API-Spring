package com.example.api.user.dto;

import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    @NotBlank
    private String username;
    
    @NotNull
    private UserGenderEnum gender;
    
    @NotNull
    @PositiveOrZero
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
    @PositiveOrZero
    private Integer mannerScore;
    
    @NotNull
    private Boolean isActive;
}