package com.example.api.sms.application.port.in;

import com.amazonaws.services.sns.model.PublishResult;

public interface SendCertificationCodeUsecase {
    PublishResult send(String phone);
}
