package com.example.api.s3.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@RequiredArgsConstructor
public class AwsS3Config {
    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;
    
    @Bean
    public AwsCredentials awsCredentials() {
        return AwsBasicCredentials.create(accessKey, secretKey);
    }
    
    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of("ap-northeast-2"))
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials()))
                .build();
    }
}