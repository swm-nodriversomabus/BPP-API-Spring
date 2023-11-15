package com.example.api.sms.repository;

import com.example.api.sms.adapter.out.persistence.VerifyPhoneCertification;
import org.springframework.data.repository.CrudRepository;

public interface VerifyCertificationRepository extends CrudRepository<VerifyPhoneCertification, String> {

}