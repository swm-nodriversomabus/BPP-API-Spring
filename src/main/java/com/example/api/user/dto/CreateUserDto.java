package com.example.api.user.dto;

import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

// User 셍성 DTO
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserDto {
    @NotBlank(message = "소셜 로그인 값이 없습니다")
    String socialEmail; // 소셜 이메일

    @NotBlank(message = "소셜 로그인 값이 없습니다")
    String provider; // 프로바이더 값

    // 위 두개는 소셜에서 데이터를 가져오는 용도

    Long socialId;

    @NotBlank(message = "이름을 입력해주세요")
    @Size(max = 30, message = "이름은 최대 30글자입니다")
    String username;

    UserGenderEnum gender;

    @NotNull(message = "나이를 입력해주세요")
    Integer age;

    @NotBlank(message = "휴대폰 번호를 입력해주세요")
    @Pattern(
            regexp = "(01[016789])(\\d{3,4})(\\d{4})",
            message = "올바른 휴대폰 번호를 입력해주세요"
    )
    String phone;

    @Builder.Default
    UserRoleEnum role = UserRoleEnum.User;
    
    @Builder.Default
    Boolean blacklist = false;
    
    @Builder.Default
    String stateMessage = "";
    
    @Builder.Default
    Integer mannerScore = 30;
    
    @Builder.Default
    Boolean isActive = true;
}