package com.example.api.multipart.application.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileUsecase {
    public void uploadFile(MultipartFile file);
}