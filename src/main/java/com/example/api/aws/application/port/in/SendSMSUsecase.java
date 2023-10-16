package com.example.api.aws.application.port.in;

import com.amazonaws.services.sns.model.PublishResult;
import com.example.api.aws.dto.SendSMSDto;

public interface SendSMSUsecase {
    PublishResult send(SendSMSDto sendSMSDto);
}
