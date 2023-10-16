package com.example.api.aws.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SendSMSDto {
    @NotBlank(message = "핸드폰 번호를 입력해주세요")
    @Pattern(
            regexp = "(01[016789])(\\d{3,4})(\\d{4})",
            message = "올바른 핸드폰 번호를 입력해주세요"
    )
    String phone;
}
