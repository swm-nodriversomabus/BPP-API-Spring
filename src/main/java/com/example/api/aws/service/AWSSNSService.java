package com.example.api.aws.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.example.api.aws.application.port.in.SendSMSUsecase;
import com.example.api.aws.config.AWSConfig;
import com.example.api.aws.dto.SendSMSDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Slf4j
@RequiredArgsConstructor
@Service
public class AWSSNSService implements SendSMSUsecase {
    private final AWSConfig awsConfig;
    @Override
    public PublishResult send(String phone) {

        PublishResult result = null;
        StringBuilder koreaPhone = new StringBuilder("+82");
        koreaPhone.append(phone);
        String region = awsConfig.getAwsRegion();
        try {

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
                    generateRandomSixDigitNumber(),
                    koreaPhone.toString(),
                    smsAttributes);
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
        String message = "[여행파티]인증번호입니다 아래 6글자를 입력해주세요\n";
        StringBuilder newMessage = new StringBuilder(message);
        int randomNumber = random.nextInt(1000000); // 0~ 999999 사이 랜덤 숫자
        newMessage.append(String.format("%06d", randomNumber));
        return newMessage.toString();
    }

    private PublishResult sendSMSMessage(AmazonSNS sns, String message, String phone, Map<String, MessageAttributeValue> messageAttributeValueMap) {
        return sns.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phone)
                .withMessageAttributes(messageAttributeValueMap)
        );
    }
}
