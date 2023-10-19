package com.example.api.common.exception;

import com.example.api.common.type.ErrorCodeEnum;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private ErrorCodeEnum errorCodeEnum;
    private String message;
    private String result;
    public CustomException(ErrorCodeEnum e) {
        this.result = "ERROR";
        this.errorCodeEnum = e;
        this.message = e.getMessage();
    }
}