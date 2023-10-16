package com.example.api.aws.adapter.in;


import com.example.api.aws.application.port.in.SendSMSUsecase;
import com.example.api.aws.dto.SendSMSDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aws/sns")
@Validated
public class AWSSNSController {
    private final SendSMSUsecase sendSMSUsecase;


    /**
     * 핸드폰 인증에 사용
     * @param sendSMSDto
     */
    @GetMapping("/code/{phone}")
    public void sendCertificationPhone(@PathVariable String phone){
        sendSMSUsecase.send(phone);
    }


}
