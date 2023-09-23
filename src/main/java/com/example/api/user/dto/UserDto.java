package com.example.api.user.dto;

import com.example.api.user.adapter.out.persistence.UserEntity;
import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    private UserGenderEnum gender;
    
    @NotEmpty
    private Integer age;
    
    @NotEmpty
    private String phone;
    
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

    private String naverId;

    private String kakaoId;

    private String googleId;

    private String instaId;

    private String appleId;
}