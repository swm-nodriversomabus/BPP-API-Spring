package com.example.api.sms.repository;

import com.example.api.sms.adapter.out.persistence.PhoneCertification;
import org.springframework.data.repository.CrudRepository;

public interface PhoneCertificationRepository extends CrudRepository<PhoneCertification, String> {
//    Optional<PhoneCertification> findByPhone(String phone);

}