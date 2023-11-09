package com.example.api.sms.application.port.out;

public interface CheckVerifiedPhonePort {
    void findCheckedPhone(String phone);

    void saveCheckedPhone(String phone);
}
