package com.example.api.s3.adapter.in.rest;

import com.example.api.auth.domain.SecurityUser;
import com.example.api.common.exception.CustomException;
import com.example.api.common.type.ErrorCodeEnum;
import com.example.api.common.utils.AuthenticationUtils;
import com.example.api.s3.application.port.in.FileDisplayUsecase;
import com.example.api.s3.application.port.in.FileUploadUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "S3", description = "S3 API")
public class S3Controller {
    private final FileUploadUsecase fileUploadUsecase;
    private final FileDisplayUsecase fileDisplayUsecase;

    /**
     * 파일 업로드
     * @param file (데이터)
     * @return filename
     */
    @Operation(summary = "Upload file", description = "S3에 파일을 업로드한다.")
    @PostMapping("/file/upload")
    public String upload(@RequestBody MultipartFile file) {
        SecurityUser securityUser = AuthenticationUtils.getCurrentUserAuthentication();
        if (securityUser == null) {
            log.error("S3Controller::upload: Login is needed");
            throw new CustomException(ErrorCodeEnum.LOGIN_IS_NOT_DONE);
        }
        return fileUploadUsecase.upload(file);
    }

    /**
     * 파일 조회
     * @param filename (파일명)
     * @return file
     */
    @Operation(summary = "Display file", description = "S3에 업로드한 파일을 조회한다.")
    @GetMapping("/file/{filename}")
    public ResponseEntity<InputStreamResource> display(@PathVariable String filename) {
        InputStream fileStream = fileDisplayUsecase.display(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(fileStream));
    }
}