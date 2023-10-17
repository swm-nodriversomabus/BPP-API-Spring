package com.example.api.sms.adapter.out.persistence;


import com.example.api.sms.application.port.out.CertificationCodePort;
import com.example.api.sms.repository.PhoneCertificationRepository;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PhoneCertificationPersistence implements CertificationCodePort {
    private final PhoneCertificationRepository repository;
    @Override
    public void saveCode(String phone, String code) {
        repository.save(new PhoneCertification(phone, code));
    }

    @Override
    public PhoneCertification findCode(String phone) {
        return repository.findById(phone).orElseThrow(()-> new CustomException(ErrorCodeEnum.CODE_IS_EXPIRED));
    }
}
