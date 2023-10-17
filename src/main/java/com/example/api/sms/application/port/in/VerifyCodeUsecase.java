package com.example.api.sms.application.port.in;

import com.example.api.sms.dto.CheckSMSDto;

public interface VerifyCodeUsecase {
    void verifyCertificationCode(CheckSMSDto checkSMSDto);
}
