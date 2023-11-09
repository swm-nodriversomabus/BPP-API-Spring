package com.example.api.sms.application.port.out;

import com.example.api.sms.adapter.out.persistence.PhoneCertification;

public interface CertificationCodePort {
    void saveCode(String phone, String code);

    PhoneCertification findCode(String phone);
}
