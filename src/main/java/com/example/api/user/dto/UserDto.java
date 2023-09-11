package com.example.api.user.dto;

import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String nickname;
    
    @NotNull
    private UserGenderEnum gender;
    
    @NotNull
    @Min(0)
    private Integer age;
    
    @NotBlank
    private String phone;
    
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    private String address;
    
    @NotNull
    private UserRoleEnum role;
    
    @NotNull
    private Boolean blacklist;
    
    @NotEmpty
    private String personality;
    
    @NotEmpty
    private String stateMessage;
    
    @NotNull
    @Min(0)
    private Integer mannerScore;
    
    @NotNull
    private Long createdUserId;
    
    @NotNull
    private Long updatedUserId;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @NotNull
    private Boolean isActive;
}