package com.example.api.s3.application.port.in;

import java.io.InputStream;

public interface FileDisplayUsecase {
    InputStream display(String filename);
}