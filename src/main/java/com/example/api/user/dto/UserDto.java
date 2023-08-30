package com.example.api.user.dto;

import com.example.api.user.adapter.out.persistence.UserEntity;
import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import jakarta.validation.constraints.NotEmpty;
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
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String nickname;
    
    @NotEmpty
    private UserGenderEnum gender;
    
    @NotEmpty
    private Integer age;
    
    @NotEmpty
    private String phone;
    
    @NotEmpty
    private String email;
    
    @NotEmpty
    private String address;
    
    @NotEmpty
    private UserRoleEnum role;
    
    @NotEmpty
    private Boolean blacklist;
    
    @NotEmpty
    private String personality;
    
    @NotEmpty
    private String stateMessage;
    
    @NotEmpty
    private Integer mannerScore;
    
    @NotEmpty
    private Long createdUserId;
    
    @NotEmpty
    private Long updatedUserId;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @NotEmpty
    private Boolean isActive;
}