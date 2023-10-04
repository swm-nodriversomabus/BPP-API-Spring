package com.example.api.multipart.adapter.in.rest;

import com.example.api.multipart.application.port.in.ShowFileUsecase;
import com.example.api.multipart.application.port.in.UploadFileUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Tag(name = "Multipart", description = "Multipart API")
public class FileController {
    private final UploadFileUsecase uploadFileUsecase;
    private final ShowFileUsecase showFileUsecase;

    /**
     * 파일 업로드
     * @param file (데이터)
     */
    @Operation(summary = "Upload File", description = "파일을 업로드한다.")
    @PostMapping("/upload")
    public void uploadFile(MultipartFile file) {
        uploadFileUsecase.uploadFile(file);
    }

    /**
     * 파일 조회
     * @param fileName (파일명)
     * @return ResponseEntity<byte[]>
     */
    @Operation(summary = "Show File", description = "파일을 조회한다.")
    @GetMapping("/image")
    public ResponseEntity<byte[]> getFile(String fileName) {
        return showFileUsecase.getFile(fileName);
    }
}