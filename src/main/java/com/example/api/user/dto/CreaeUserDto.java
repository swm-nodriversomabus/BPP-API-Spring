package com.example.api.user.dto;


import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

// user 셍성 DTO
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreaeUserDto {
    @NotBlank(message = "소셜 로그인 값이 없습니다")
    String socialEmail; // 소셜 이메일

    @NotBlank(message = "소셜 로그인 값이 없습니다")
    String provider; // 프로바이더 값

    // 위 두개는 소셜에서 데이터를 가져오는 용

    Integer socialId;

    @NotBlank(message = "이름을 입력해주세요")
    @Size(max=30, message = "이름은 최대 30글자까지 입니다")
    String username;

    UserGenderEnum gender;

    @NotNull(message = "나이를 입력해주세요")
    Integer age;

    @NotBlank(message = "핸드폰 번호를 입력해주세요")
    @Pattern(
            regexp = "(01[016789])(\\d{3,4})(\\d{4})",
            message = "올바른 핸드폰 번호를 입력해주세요"
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

//    public CreaeUserDto(String username, UserGenderEnum gender, Integer age, String phone, UserRoleEnum role, Boolean blacklist, String stateMessage, Integer mannerScore, Boolean isActive){
//        this.username = username;
//        this.gender = gender;
//        this.age = age;
//        this.phone = phone;
//
//        this.role = role;
//        this.blacklist = blacklist;
//        this.stateMessage = stateMessage;
//        this.mannerScore = mannerScore;
//        this.isActive = isActive;
//    }
}
