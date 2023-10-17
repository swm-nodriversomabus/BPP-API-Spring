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
@RequestMapping("/sms")
@Validated
public class SmsController {
    private final SendCertificationCodeUsecase sendCertificationCodeUsecase;
    private final VerifyCodeUsecase verifyCodeUsecase;

    /**
     * 핸드폰 인증에 사용
     *
     * @param phone
     */
    @Operation(summary = "certification phone", description = "핸드폰 인증")
    @GetMapping("/code/{phone}")
    public void sendCertificationPhone(@PathVariable String phone){
        sendCertificationCodeUsecase.send(phone);
    }

    @PostMapping("/code")
    public void certificatePhone(@RequestBody @Validated CheckSMSDto checkSMSDto){
        verifyCodeUsecase.verifyCertificationCode(checkSMSDto);
    }

}
