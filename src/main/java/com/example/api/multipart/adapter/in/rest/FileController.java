package com.example.api.multipart.adapter.in.rest;

import com.example.api.multipart.application.port.in.ShowFileUsecase;
import com.example.api.multipart.application.port.in.UploadFileUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping("/file")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return uploadFileUsecase.uploadFile(file);
    }

    /**
     * 파일 조회
     * @param fileName (파일명)
     * @return file
     */
    @Operation(summary = "Show File", description = "파일을 조회한다.")
    @GetMapping("/file/local/{fileName}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileName) {
        return showFileUsecase.getFile(fileName);
    }
    
    // 요즘 이미지들은 용량이 커서 썸네일 만들고 불러오는 기능도 필요함
}