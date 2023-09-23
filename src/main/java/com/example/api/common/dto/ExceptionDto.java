package com.example.api.common.dto;


import com.example.api.common.type.ErrorCodeEnum;
import lombok.*;

@Getter
public class ExceptionDto {
    private String result;
    private ErrorCodeEnum status;
    private String statusMessage;

    public ExceptionDto(ErrorCodeEnum e){
        this.result = "ERROR";
        this.status = e;
        this.statusMessage = e.getMessage();
    }

    public ExceptionDto(ErrorCodeEnum e, String message) {
        this.result = "ERROR";
        this.statusMessage = message;
        this.status = e;
    }
}
