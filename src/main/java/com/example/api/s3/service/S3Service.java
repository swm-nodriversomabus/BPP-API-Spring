package com.example.api.s3.service;

import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.s3.application.port.in.FileDisplayUsecase;
import com.example.api.s3.application.port.in.FileUploadUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class S3Service implements FileUploadUsecase, FileDisplayUsecase {
    private final S3Client s3Client;
    
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    
    /*@Autowired
    public S3Service(S3Client s3Client, @Value("${cloud.aws.s3.bucket}") String bucket) {
        this.s3Client = s3Client;
        this.bucket = "yeohaengparty-image";
    }*/
    
    @Override
    public String upload(MultipartFile file) {
        try {
            String objectKey = UUID.randomUUID().toString();
            s3Client.putObject(PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(objectKey)
                    .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            return "https://" + bucket + "s3.amazonaws.com/" + objectKey;
        } catch (S3Exception e) {
            throw new CustomException(ErrorCodeEnum.S3_UPLOAD_FAIL);
        } catch (IOException e) {
            throw new CustomException(ErrorCodeEnum.FILE_READ_ERROR);
        }
    }
    
    @Override
    public InputStream display(String objectKey) {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucket)
                    .key(objectKey)
                    .build();
            return s3Client.getObject(getObjectRequest);
        } catch (S3Exception e) {
            throw new CustomException(ErrorCodeEnum.S3_DISPLAY_FAIL);
        }
    }
}