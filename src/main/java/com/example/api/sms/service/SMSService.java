package com.example.api.sms.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.sms.application.port.in.SendCertificationCodeUsecase;
import com.example.api.sms.application.port.in.VerifyCodeUsecase;
import com.example.api.sms.application.port.out.CertificationCodePort;
import com.example.api.common.config.AWSConfig;
import com.example.api.sms.application.port.out.CheckVerifiedPhonePort;
import com.example.api.sms.dto.CheckSMSDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Slf4j
@RequiredArgsConstructor
@Service
public class SMSService implements SendCertificationCodeUsecase, VerifyCodeUsecase {
    private final AWSConfig awsConfig;
    private final CertificationCodePort certificationCodePort;
    private final CheckVerifiedPhonePort checkVerifiedPhonePort;

    @Override
    public void verifyCertificationCode(CheckSMSDto checkSMSDto) {
        String phone = checkSMSDto.getPhone();
        String code = checkSMSDto.getCode();

        String originalCode = certificationCodePort.findCode(phone).getCode();
        if (code.equals(originalCode)) {
            // 이제 그 redis에 저장 => 인증 redis
            checkVerifiedPhonePort.saveCheckedPhone(phone);
        }else{
            throw new CustomException(ErrorCodeEnum.CODE_IS_NOT_VALID); // 코드가 일치 하지 않음
        }

    }

    /**
     * 코드 발급 받고, 전송
     * @param phone
     * @return
     */
    @Override
    public PublishResult send(String phone) {

        PublishResult result = null;
        StringBuilder koreaPhone = new StringBuilder("+82");
        koreaPhone.append(phone);
        try {
            String message = "[여행파티]인증번호입니다 아래 6글자를 입력해주세요\n";
            String code = generateRandomSixDigitNumber();
            StringBuilder newMessage = new StringBuilder(message);
            newMessage.append(code);
            BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsConfig.getAwsAccessKey(), awsConfig.getAwsSecretKey());

            AmazonSNSClientBuilder builder =
                    AmazonSNSClientBuilder.standard();

            AmazonSNS sns = builder.withRegion(Regions.AP_NORTHEAST_1)
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

            Map<String, MessageAttributeValue> smsAttributes =
                    new HashMap<>();

            smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                    .withStringValue("BPP").withDataType("String"));

            smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
                    .withStringValue("0.50").withDataType("String"));

            smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                    .withStringValue("Promotional").withDataType("String"));

            result = this.sendSMSMessage(sns,
                    newMessage.toString(),
                    koreaPhone.toString(),
                    smsAttributes);
            certificationCodePort.saveCode(phone,code);
        } catch (Exception ex) {

            log.error("The sms was not sent.");
            log.error("Error message: " + ex.getMessage());
            throw new AmazonClientException(ex.getMessage(), ex);
        }
        return result;
    }

    /**
     * 6 랜덤 숫자 리턴
     *
     * @return
     */
    private String generateRandomSixDigitNumber() {
        Random random = new Random();

        int randomNumber = random.nextInt(1000000); // 0~ 999999 사이 랜덤 숫자
        return String.format("%06d", randomNumber);
//        newMessage.append();
//        return newMessage.toString();
    }

    private PublishResult sendSMSMessage(AmazonSNS sns, String message, String phone, Map<String, MessageAttributeValue> messageAttributeValueMap) {
        return sns.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phone)
                .withMessageAttributes(messageAttributeValueMap)
        );
    }
}
