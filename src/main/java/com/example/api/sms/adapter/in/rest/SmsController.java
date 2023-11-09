package com.example.api.sms.adapter.in.rest;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.common.utils.AuthenticationUtils;
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
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("SmsController::sendCertificationPhone: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        sendCertificationCodeUsecase.send(phone);
    }

    /**
     * 휴대폰 인증
     * @param checkSMSDto (데이터)
     */
    @PostMapping("/sms/code")
    public void certificatePhone(@Valid @RequestBody CheckSMSDto checkSMSDto) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("SmsController::certificatePhone: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        verifyCodeUsecase.verifyCertificationCode(checkSMSDto);
    }
}