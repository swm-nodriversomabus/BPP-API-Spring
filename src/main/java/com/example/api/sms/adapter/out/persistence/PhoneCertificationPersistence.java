package com.example.api.sms.adapter.out.persistence;

import com.example.api.sms.application.port.out.CertificationCodePort;
import com.example.api.sms.application.port.out.CheckVerifiedPhonePort;
import com.example.api.sms.repository.PhoneCertificationRepository;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.sms.repository.VerifyCertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PhoneCertificationPersistence implements CertificationCodePort, CheckVerifiedPhonePort {
    private final PhoneCertificationRepository repository;
    private final VerifyCertificationRepository verifyCertificationRepository;
    @Override
    public void saveCode(String phone, String code) {
        repository.save(new PhoneCertification(phone, code));
    }

    @Override
    public PhoneCertification findCode(String phone) {
        return repository.findById(phone).orElseThrow(()-> new CustomException(ErrorCodeEnum.CODE_IS_EXPIRED));
    }

    /**
     * 인증 번호 만료 여부 확인
     * @param phone (휴대폰 번호)
     */
    @Override
    public void findCheckedPhone(String phone) {
        verifyCertificationRepository.findById(phone).orElseThrow(()-> new CustomException(ErrorCodeEnum.CODE_IS_EXPIRED));
    }

    /**
     * 인증 여부 저장
     * @param phone (휴대폰 번호)
     */
    @Override
    public void saveCheckedPhone(String phone) {
        verifyCertificationRepository.save(new VerifyPhoneCertification(phone));
    }
}