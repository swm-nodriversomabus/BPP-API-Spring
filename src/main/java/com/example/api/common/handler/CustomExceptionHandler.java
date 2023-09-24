package com.example.api.common.handler;


import com.example.api.common.dto.ExceptionDto;
import com.example.api.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    // 글로벌 예외처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> exceptionHandler(CustomException e) {
        log.warn(e.getMessage());
        return ResponseEntity.status(e.getErrorCodeEnum().getHttpStatus())
                .body(new ExceptionDto(e.getErrorCodeEnum()));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String runtimeExceptionHandler(RuntimeException e){
        log.error(e.getMessage());
        return "Internal Server Error while Running";
    }
}
