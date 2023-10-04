package com.example.api.multipart.application.port.in;

import org.springframework.http.ResponseEntity;

public interface ShowFileUsecase {
    public ResponseEntity<byte[]> getFile(String fileName);
}