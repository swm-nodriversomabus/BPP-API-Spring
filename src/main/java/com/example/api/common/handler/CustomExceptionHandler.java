package com.example.api.common.handler;


import com.example.api.common.dto.ExceptionDto;
import com.example.api.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    // 글로벌 예외처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> exceptionHandler(CustomException e){
        log.error(e.getMessage());
        return ResponseEntity.status(e.getErrorCodeEnum().getHttpStatus())
                .body(new ExceptionDto(e.getErrorCodeEnum()));
    }
}
