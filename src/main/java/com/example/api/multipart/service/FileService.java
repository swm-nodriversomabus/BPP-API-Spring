package com.example.api.multipart.service;

import com.example.api.multipart.application.port.in.ShowFileUsecase;
import com.example.api.multipart.application.port.in.UploadFileUsecase;
import com.example.api.multipart.property.FileUploadProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService implements UploadFileUsecase, ShowFileUsecase {
    private final Path uploadPath;
    
    @Autowired
    public FileService(FileUploadProperties fileUploadProperties) {
        this.uploadPath = Paths.get(fileUploadProperties.getLocation())
                .toAbsolutePath().normalize();
    }
    
    @Override
    @Transactional
    public void uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = UUID.randomUUID().toString();
        File saveFile = new File(uploadPath + "\\" + newFileName + fileExtension);
        
        try {
            file.transferTo(saveFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public ResponseEntity<byte[]> getFile(String fileName) {
        ResponseEntity<byte[]> data = null;
        
        try {
            String srcFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);
            File file = new File(uploadPath + File.separator + srcFileName);
            HttpHeaders headers = new HttpHeaders();
            
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            data = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
        
        return data;
    }
}