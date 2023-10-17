package com.example.api.sms.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CheckSMSDto {
    @NotBlank(message = "핸드폰 번호를 입력해주세요")
    @Pattern(
            regexp = "(01[016789])(\\d{3,4})(\\d{4})",
            message = "올바른 핸드폰 번호를 입력해주세요"
    )
    String phone;

    @NotBlank(message = "인증번호를 입력해주세요")
    @Pattern(regexp = "^[0-9]{6}$", message = "숫자 6자리만 입력해야 합니다.")
    String code;
}
