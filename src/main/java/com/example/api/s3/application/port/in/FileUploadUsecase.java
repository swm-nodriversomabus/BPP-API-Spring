package com.example.api.s3.application.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadUsecase {
    String upload(MultipartFile file);
}