package com.example.api.sms.adapter.in.rest;

import com.example.api.sms.application.port.in.SendCertificationCodeUsecase;
import com.example.api.sms.application.port.in.VerifyCodeUsecase;
import com.example.api.sms.dto.CheckSMSDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SmsController {
    private final SendCertificationCodeUsecase sendCertificationCodeUsecase;
    private final VerifyCodeUsecase verifyCodeUsecase;

    /**
     * 휴대폰 인증에 사용
     * @param phone (전화번호)
     */
    @Operation(summary = "Certificate phone", description = "휴대폰 인증")
    @GetMapping("/sms/code/{phone}")
    public void sendCertificationPhone(@PathVariable String phone) {
        sendCertificationCodeUsecase.send(phone);
    }

    /**
     * 휴대폰 인증
     * @param checkSMSDto (데이터)
     */
    @PostMapping("/sms/code")
    public void certificatePhone(@Valid @RequestBody CheckSMSDto checkSMSDto) {
        verifyCodeUsecase.verifyCertificationCode(checkSMSDto);
    }
}