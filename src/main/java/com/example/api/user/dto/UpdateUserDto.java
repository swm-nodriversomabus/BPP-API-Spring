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
public class UpdateUserDto { // 추후 UpdateUserDto로 바꾸기
    @NotBlank
    private String username;
    
    @NotNull
    private UserGenderEnum gender;
    
    @NotNull
    @Min(0)
    private Integer age;
    
    @NotBlank
    private String phone;
    
    @NotNull
    private UserRoleEnum role;
    
    @NotNull
    private Boolean blacklist;
    
    @NotEmpty
    private String stateMessage;
    
    @NotNull
    @Min(0)
    private Integer mannerScore;
    
    @NotNull
    private Boolean isActive;
}