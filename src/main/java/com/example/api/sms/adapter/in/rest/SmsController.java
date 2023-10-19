package com.example.api.sms.adapter.in.rest;

import com.example.api.sms.application.port.in.SendCertificationCodeUsecase;
import com.example.api.sms.application.port.in.VerifyCodeUsecase;
import com.example.api.sms.dto.CheckSMSDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class SmsController {
    private final SendCertificationCodeUsecase sendCertificationCodeUsecase;
    private final VerifyCodeUsecase verifyCodeUsecase;

    /**
     * 휴대폰 인증에 사용
     * @param phone (전화번호)
     */
    @Operation(summary = "Certificate phone", description = "휴대폰 인증")
    @GetMapping("/sms/code/{phone}")
    public void sendCertificationPhone(@PathVariable String phone){
        sendCertificationCodeUsecase.send(phone);
    }

    @PostMapping("/sms/code")
    public void certificatePhone(@RequestBody @Validated CheckSMSDto checkSMSDto){
        verifyCodeUsecase.verifyCertificationCode(checkSMSDto);
    }
}